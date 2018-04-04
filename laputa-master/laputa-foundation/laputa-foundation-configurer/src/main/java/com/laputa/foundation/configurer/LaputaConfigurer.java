package com.laputa.foundation.configurer;

import com.laputa.foundation.configurer.core.*;
import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import com.laputa.foundation.configurer.listener.ConfListenerFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.*;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 属性配置类
 * environmentPropertySource       :   java -D 中所带参数属性
 * localSysConfigPropertySource    :   配置文件 ( classpath:/laputa-config/sys_config.properties ) 属性
 * cloudZookeeperPropertySource    :   ZK 目录 /laputa/${app.name}/${app.ver}/cloudPropertySources
 * <p>
 * org.springframework.beans.factory.support.AbstractBeanFactory#embeddedValueResolvers 解析器列表
 * <p>
 * Created by jiangdongping on 2018/3/23 0023.
 */
public final class LaputaConfigurer extends PlaceholderConfigurerSupport implements EnvironmentAware, ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(LaputaConfigurer.class);

    private static final LaputaConfigurer instance = new LaputaConfigurer();

    private static final String ROOT_PROPERTYSOURCE_NAME = "root";
    private static final String COMMA = ",";
    private static final String PATH_SEPARATOR = "/";

    public static final String PATH_ROOT_PREFIX = "/laputa/config";
    public static final String GROUP_PATH_ROOT_PREFIX = PATH_ROOT_PREFIX + "/groups";

    /**
     * 云端同步类型参数 分割标记
     * eg : ${a.b.value:@xxx} 表示属性key为 a.b.value, 默认值为 xxx, 并且从云端同步
     */
    private static final String CLOUD_SPLIT_FLAG = "@";

    /**
     * 应用名称
     */
    private static final String APP_NAME_KEY = "laputa.app.name";

    /**
     * 应用版本
     */
    private static final String APP_VER_KEY = "laputa.app.ver";

    /**
     * 应用分组, 逗号分割
     */
    private static final String APP_GROUPS_KEY = "laputa.app.groups";


    /**
     * 允许配置中心离线启动
     * 警告 : 允许离线启动, 虽然配置中心重连后能重新加载配置,但是部分需要init 的bean,行为不可预料
     */
    private static final String CLOUD_ENABLE_CONFIG_OFFLINE_START = "laputa.cloud.enable.config.offline.start";

    /**
     * 云端配置是否覆盖本地
     */
    private static final String CLOUD_CLOUD_ZOOKEEPER_ADDRESS = "laputa.cloud.zookeeper.address";

    /**
     * 云端配置是否覆盖本地
     */
    private static final String CLOUD_OVERRIDE_KEY = "laputa.cloud.override";


    /**
     * {@value} is the name given to the {@link PropertySource} for the set of
     * {@linkplain #mergeProperties() merged properties} supplied to this configurer.
     */
    public static final String LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME = "localProperties";

    /**
     * {@value} is the name given to the {@link PropertySource} that wraps the
     * {@linkplain #setEnvironment environment} supplied to this configurer.
     */
    public static final String ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME = "environmentProperties";


    @Nullable
    private MutablePropertySources propertySources;

    @Nullable
    private PropertySources appliedPropertySources;

    @Nullable
    private Environment environment;


    private String appName;

    private String appVer;

    private String[] groups;

    private PropertySourcesPropertyResolver propertyResolver;

    private PropertySource<Environment> environmentPropertySource;

    private PropertiesPropertySource localSysConfigPropertySource;

    private PropertiesPropertySource cloudZookeeperPropertySource;

    private Properties cloudRootProperties;

    private List<PrepPropertyNodeData> injectByPropertyNodePrepNodeDatas = Collections.synchronizedList(new ArrayList<PrepPropertyNodeData>());
    private List<PrepFieldNodeData> injectByFileldNodePrepNodeDatas = Collections.synchronizedList(new ArrayList<PrepFieldNodeData>());
    private List<PrepMethodNodeData> injectByMethodNodeDatas = Collections.synchronizedList(new ArrayList<PrepMethodNodeData>());


    private boolean cloudOverride = true;

    private boolean enableConfigOfflineStart = false;

    private CuratorFramework curatorZookeeperClient;
    private String rootPath;


    private LaputaConfigurer() {
    }

    public static LaputaConfigurer getInstance() {
        return instance;
    }

    /**
     * Customize the set of {@link PropertySources} to be used by this configurer.
     * Setting this property indicates that environment property sources and local
     * properties should be ignored.
     *
     * @see #postProcessBeanFactory
     */
    public void setPropertySources(PropertySources propertySources) {
        this.propertySources = new MutablePropertySources(propertySources);
    }

    /**
     * {@inheritDoc}
     * <p>{@code PropertySources} from this environment will be searched when replacing ${...} placeholders.
     *
     * @see #setPropertySources
     * @see #postProcessBeanFactory
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    /**
     * {@inheritDoc}
     * <p>Processing occurs by replacing ${...} placeholders in bean definitions by resolving each
     * against this configurer's set of {@link PropertySources}, which includes:
     * <ul>
     * <li>all {@linkplain org.springframework.core.env.ConfigurableEnvironment#getPropertySources
     * environment property sources}, if an {@code Environment} {@linkplain #setEnvironment is present}
     * <li>{@linkplain #mergeProperties merged local properties}, if {@linkplain #setLocation any}
     * {@linkplain #setLocations have} {@linkplain #setProperties been}
     * {@linkplain #setPropertiesArray specified}
     * <li>any property sources set by calling {@link #setPropertySources}
     * </ul>
     * <p>If {@link #setPropertySources} is called, <strong>environment and local properties will be
     * ignored</strong>. This method is designed to give the user fine-grained control over property
     * sources, and once set, the configurer makes no assumptions about adding additional sources.
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (this.propertySources == null) {

            this.propertySources = new MutablePropertySources();
            this.propertyResolver = new PropertySourcesPropertyResolver(propertySources);

            /**
             * 1.加载系统属性
             */
            if (this.environment != null) {
                this.environmentPropertySource = new PropertySource<Environment>(ENVIRONMENT_PROPERTIES_PROPERTY_SOURCE_NAME, this.environment) {
                    @Override
                    @Nullable
                    public String getProperty(String key) {
                        return this.source.getProperty(key);
                    }
                };
            }

            /**
             * 2.加载 配置文件 ( classpath:/laputa-config/sys_config.properties ) 属性
             */
            try {
                localSysConfigPropertySource = new PropertiesPropertySource(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME, mergeProperties());
            } catch (IOException ex) {
                throw new BeanInitializationException("Could not load properties", ex);
            }


            this.propertySources.addLast(this.environmentPropertySource);
            if (this.localOverride) {
                this.propertySources.addFirst(localSysConfigPropertySource);
            } else {
                this.propertySources.addLast(localSysConfigPropertySource);
            }


            this.appName = this.propertyResolver.getProperty(APP_NAME_KEY);
            this.appVer = this.propertyResolver.getProperty(APP_VER_KEY);
            this.groups = StringUtils.split(this.propertyResolver.getProperty(APP_GROUPS_KEY), COMMA);
            this.cloudOverride = Boolean.TRUE.toString().equalsIgnoreCase(
                    this.propertyResolver.getProperty(CLOUD_OVERRIDE_KEY));


            /**
             * 3.加载云端配置
             */
            if (Boolean.TRUE.toString().equalsIgnoreCase(this.propertyResolver.getProperty(CLOUD_ENABLE_CONFIG_OFFLINE_START))) {
                this.enableConfigOfflineStart = true;
            } else {
                this.enableConfigOfflineStart = false;
            }
            String zookeeperAddress = this.propertyResolver.getProperty(CLOUD_CLOUD_ZOOKEEPER_ADDRESS);
            if (StringUtils.hasLength(zookeeperAddress)) {
                cloudRootProperties = new Properties();
                this.cloudZookeeperPropertySource = new PropertiesPropertySource(ROOT_PROPERTYSOURCE_NAME, cloudRootProperties);
                rootPath = toRootPath(appName, appVer);
                rootPath = "/xxl-conf";

                try {
                    this.curatorZookeeperClient = buildCuratorZookeeperClient(zookeeperAddress, nullValue);
                    List<String> children = this.curatorZookeeperClient.getChildren().forPath(rootPath);
                    if (!CollectionUtils.isEmpty(children)) {
                        for (String child : children) {
                            byte[] data = this.curatorZookeeperClient.getData().forPath(rootPath + PATH_SEPARATOR + child);
                            if (data != null && data.length > 0) {
                                try {
                                    cloudRootProperties.put(child, new String(data, "UTF-8"));
                                } catch (Exception e) {
                                    logger.error("{0}/{1} 节点数据解析异常", rootPath, child);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("云配置连接异常 {} {} {}", this.rootPath, zookeeperAddress, e);
                    if (!this.enableConfigOfflineStart) {
                        throw LaputaConfigurerException.ExceptionEnum
                                .NORMAL_CONFIGURER_FAIL.generateException("无法链接到配置中心 {0} {1}", zookeeperAddress, rootPath);
                    }
                }


                //TODO
                if (this.cloudOverride) {
                    this.propertySources.addFirst(cloudZookeeperPropertySource);
                } else {
                    this.propertySources.addLast(cloudZookeeperPropertySource);
                }
            }


        }

        processProperties(beanFactory, this.propertyResolver);
        this.appliedPropertySources = this.propertySources;
    }

    private String toRootPath(String appName, String appVer) {
        return PATH_ROOT_PREFIX + appName + PATH_SEPARATOR + appVer;
    }

    private CuratorFramework buildCuratorZookeeperClient(String zookeeperAddress, String zookeeperAuthority) {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zookeeperAddress)
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectionTimeoutMs(5000);
        if (zookeeperAuthority != null && zookeeperAuthority.length() > 0) {
            builder = builder.authorization("digest", zookeeperAuthority.getBytes());
        }

        CuratorFramework client;
        client = builder.build();
        client.start();

        return client;
    }


    /**
     * Visit each bean definition in the given bean factory and attempt to replace ${...} property
     * placeholders with values from the given properties.
     */
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     final ConfigurablePropertyResolver propertyResolver) throws BeansException {

        propertyResolver.setPlaceholderPrefix(this.placeholderPrefix);
        propertyResolver.setPlaceholderSuffix(this.placeholderSuffix);
        propertyResolver.setValueSeparator(this.valueSeparator);


        StringValueResolver valueResolver = strVal -> {

            /**
             * resolved 在 ${xxx} 匹配不到时,直接返回 ${xxx}
             */
            String resolved = (ignoreUnresolvablePlaceholders ?
                    propertyResolver.resolvePlaceholders(strVal) :
                    propertyResolver.resolveRequiredPlaceholders(strVal));
            if (trimValues) {
                resolved = resolved.trim();
            }
            /**
             * 返回 null 则认为该值应该配置为 null, spring不会接着往其他 配置器内查找了
             * 返回 非 null, spring 会接着遍历其他配置器, 如果后面的还有该属性, 则会覆盖
             */
            return (resolved.equals(nullValue) ? null : resolved);
        };


        /**
         * 注册云端参数回调
         */
        final String privateBeanName = reflectBeanName();
        final BeanFactory privateBeanFactory = reflectBeanFactory();
        // visit bean definition
        String[] beanNames = beanFactoryToProcess.getBeanDefinitionNames();
        if (beanNames != null && beanNames.length > 0) {
            for (final String beanName : beanNames) {
                if (!(beanName.equals(privateBeanName) && beanFactoryToProcess.equals(privateBeanFactory))) {

                    // 1、XML('${...}')：resolves placeholders + watch
                    BeanDefinition beanDefinition = beanFactoryToProcess.getBeanDefinition(beanName);
                    MutablePropertyValues pvs = beanDefinition.getPropertyValues();
                    PropertyValue[] pvArray = pvs.getPropertyValues();
                    for (PropertyValue pv : pvArray) {
                        if (pv.getValue() instanceof TypedStringValue) {
                            TypedStringValue typedStringValue = (TypedStringValue) pv.getValue();
                            String typeStringVal = typedStringValue.getValue();

                            LaputaPlaceHolder placeHolder = new LaputaPlaceHolder(typeStringVal, trimValues);
                            if (placeHolder.isValid() && placeHolder.isCloudFlag()) {

                                typedStringValue.setValue(placeHolder.getHolder());

                                /**
                                 * 这里直接取bean会导致属性不解析, 直接get Bean 会导致立马构建对象
                                 * 但是构造器还未完全解析完成
                                 * xxl-conf 思路方式中若 zk提前触发事件也会导致bean构造不全
                                 * 故修改为在 spring 初始化完成事件中进行刷新
                                 */
                                this.injectByPropertyNodePrepNodeDatas.add(
                                        new PrepPropertyNodeData(pv.getName(), placeHolder, beanName, beanFactoryToProcess));
                            }
                        }

                    }


                    // 2、Annotation('@XxlConf')：resolves conf + watch
                    if (beanDefinition.getBeanClassName() == null) {
                        continue;
                    }
                    if (beanDefinition.getBeanClassName().indexOf("SecurityController") > 0) {
                        System.currentTimeMillis();
                    }
                    Class beanClazz = null;
                    try {
                        beanClazz = Class.forName(beanDefinition.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        logger.error(">>>>>>>>>>> xxl-conf, annotation bean class invalid, error msg:{}", e.getMessage());
                    }
                    if (beanClazz == null) {
                        continue;
                    }

                    ReflectionUtils.doWithFields(beanClazz, new ReflectionUtils.FieldCallback() {
                        @Override
                        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                            if (field.isAnnotationPresent(Value.class)) {
                                Value xxlConf = field.getAnnotation(Value.class);
                                String confKey = xxlConf.value();

                                LaputaPlaceHolder placeHolder = new LaputaPlaceHolder(confKey, trimValues);
                                if (placeHolder.isValid() && placeHolder.isCloudFlag()) {
                                    valueAntSet(xxlConf, placeHolder.getHolder());

                                    injectByFileldNodePrepNodeDatas.add(
                                            new PrepFieldNodeData(field, placeHolder, beanName, beanFactoryToProcess));
                                }


                            }
                        }
                    });

                    ReflectionUtils.doWithMethods(beanClazz, new ReflectionUtils.MethodCallback() {
                        @Override
                        public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                            if (method.isAnnotationPresent(Value.class)) {
                                Value xxlConf = method.getAnnotation(Value.class);
                                String confKey = xxlConf.value();
                                LaputaPlaceHolder placeHolder = new LaputaPlaceHolder(confKey, trimValues);
                                if (placeHolder.isValid() && placeHolder.isCloudFlag()) {
                                    valueAntSet(xxlConf, placeHolder.getHolder());
                                    injectByMethodNodeDatas.add(
                                            new PrepMethodNodeData(method, placeHolder, beanName, beanFactoryToProcess));
                                }
                            }
                        }
                    });

                }
            }
        }

        doProcessProperties(beanFactoryToProcess, valueResolver);
    }


    private void valueAntSet(Value valueAnt, String value) {
        try {
            Field fh = ReflectionUtils.findField(valueAnt.getClass(), "h");
            boolean fhPrev = fh.isAccessible();
            fh.setAccessible(true);
            Object h = fh.get(valueAnt);
            fh.setAccessible(fhPrev);

            Field fmemberValues = ReflectionUtils.findField(h.getClass(), "memberValues");
            boolean fmemberValuesPrev = fmemberValues.isAccessible();
            fmemberValues.setAccessible(true);
            Map memberValues = (Map) (fmemberValues.get(h));
            fmemberValues.setAccessible(fmemberValuesPrev);
            memberValues.put("value", value);
        } catch (Exception e) {
            logger.error("注解值修复异常 {} {}", value, e);
        }
    }

    private String reflectBeanName() {
        Field privateBeanNameFiled = ReflectionUtils.findField(this.getClass(), "beanName", String.class);
        boolean privateBeanNameFiledAccessibleTemp = privateBeanNameFiled.isAccessible();
        privateBeanNameFiled.setAccessible(true);
        String privateBeanName = (String) ReflectionUtils.getField(privateBeanNameFiled, this);
        privateBeanNameFiled.setAccessible(privateBeanNameFiledAccessibleTemp);
        return privateBeanName;
    }

    private BeanFactory reflectBeanFactory() {
        Field privateBeanFactoryFiled = ReflectionUtils.findField(this.getClass(), "beanFactory", BeanFactory.class);
        boolean privateBeanFactoryFiledAccessibleTemp = privateBeanFactoryFiled.isAccessible();
        privateBeanFactoryFiled.setAccessible(true);
        BeanFactory beanFactory = (BeanFactory) ReflectionUtils.getField(privateBeanFactoryFiled, this);
        privateBeanFactoryFiled.setAccessible(privateBeanFactoryFiledAccessibleTemp);
        return beanFactory;
    }

    private boolean xmlKeyValid(String originKey) {
        boolean start = originKey.startsWith(this.placeholderPrefix);
        boolean end = originKey.endsWith(this.placeholderSuffix);
        if (start && end) {
            return true;
        }
        return false;
    }

    private String xmlKeyParse(String originKey) {
        if (xmlKeyValid(originKey)) {
            // replace by xxl-conf
            String key = originKey.substring(placeholderPrefix.length(), originKey.length() - placeholderSuffix.length());
            return key;
        }
        return null;
    }

    /**
     * Implemented for compatibility with {@link org.springframework.beans.factory.config.PlaceholderConfigurerSupport}.
     *
     * @throws UnsupportedOperationException in this implementation
     * @deprecated in favor of {@link #processProperties(ConfigurableListableBeanFactory, ConfigurablePropertyResolver)}
     */
    @Override
    @Deprecated
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) {
        throw new UnsupportedOperationException(
                "Call processProperties(ConfigurableListableBeanFactory, ConfigurablePropertyResolver) instead");
    }

    /**
     * Returns the property sources that were actually applied during
     * {@link #postProcessBeanFactory(ConfigurableListableBeanFactory) post-processing}.
     *
     * @return the property sources that were applied
     * @throws IllegalStateException if the property sources have not yet been applied
     * @since 4.0
     */
    public PropertySources getAppliedPropertySources() throws IllegalStateException {
        Assert.state(this.appliedPropertySources != null, "PropertySources have not get been applied");
        return this.appliedPropertySources;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (this.injectByPropertyNodePrepNodeDatas != null && this.injectByPropertyNodePrepNodeDatas.size() > 0) {
            for (PrepPropertyNodeData prepNodeData : injectByPropertyNodePrepNodeDatas) {
                Object targetBean = prepNodeData.getBeanFactoryToProcess().getBean(prepNodeData.getBeanName());
                ConfListenerFactory.addListener(prepNodeData.getLaputaPlaceHolder().getProperty(),
                        new InjectByBeanWrapperNode(prepNodeData.getBeanName(), targetBean, prepNodeData.getProperty()));
            }
        }

        if (this.injectByFileldNodePrepNodeDatas != null && this.injectByFileldNodePrepNodeDatas.size() > 0) {
            for (PrepFieldNodeData prepNodeData : injectByFileldNodePrepNodeDatas) {
                Object targetBean = prepNodeData.getBeanFactoryToProcess().getBean(prepNodeData.getBeanName());
                ConfListenerFactory.addListener(prepNodeData.getLaputaPlaceHolder().getProperty(),
                        new InjectByFileldNode(prepNodeData.getBeanName(), targetBean, prepNodeData.getField()));
            }
        }

        if (this.injectByMethodNodeDatas != null && this.injectByMethodNodeDatas.size() > 0) {
            for (PrepMethodNodeData prepNodeData : injectByMethodNodeDatas) {
                Object targetBean = prepNodeData.getBeanFactoryToProcess().getBean(prepNodeData.getBeanName());
                ConfListenerFactory.addListener(prepNodeData.getLaputaPlaceHolder().getProperty(),
                        new InjectByMethodNode(prepNodeData.getBeanName(), targetBean, prepNodeData.getMethod()));
            }
        }

        if (this.curatorZookeeperClient == null) {
            return;
        }

        logger.info("注册配置云同步 {} ", this.rootPath);

        final TreeCache rootNode = new TreeCache(this.curatorZookeeperClient, this.rootPath);
        rootNode.getListenable().addListener(new LaputaTreeCacheListener(ROOT_PROPERTYSOURCE_NAME, this.rootPath, this.propertyResolver, getAppliedPropertySources()));

        try {
            rootNode.start();
        } catch (Exception e) {
            logger.error("配置云同步异常{} {}", this.rootPath, e);
        }
    }

    private class LaputaTreeCacheListener implements TreeCacheListener {

        private String source;
        private String path;
        private Properties properties;
        private PropertySourcesPropertyResolver propertyResolver;

        public LaputaTreeCacheListener(String source, String path, PropertySourcesPropertyResolver propertyResolver, PropertySources propertySources) {
            this.source = source;
            this.path = path;
            this.properties = (Properties) (propertySources.get(source).getSource());
            this.propertyResolver = propertyResolver;
        }

        @Override
        public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
            switch (event.getType()) {
                case NODE_ADDED:
                case NODE_UPDATED:
                    if (this.path.equals(event.getData().getPath())) {
                        logger.debug("跟节点开始加载 {}", this.path);
                    } else if (event.getData().getPath().startsWith(this.path)) {
                        String key = event.getData().getPath().substring(this.path.length() + PATH_SEPARATOR.length());
                        logger.debug("处理配置 {} {}", this.path, key);
                        try {
                            String curData = new String(event.getData().getData(), "UTF-8");
                            String prevData = this.properties.getProperty(key);
                            if (!curData.equals(prevData)) {
                                String vailPrevData = this.propertyResolver.getProperty(key);
                                this.properties.put(key, curData);
                                String vailCurData = this.propertyResolver.getProperty(key);
                                if (!Objects.equals(vailCurData, vailPrevData)) {
                                    logger.debug("触发 生效数据 onchane({} {} {})", key, vailPrevData, vailCurData);
                                    ConfListenerFactory.onChange(key, vailPrevData, vailCurData);
                                }

                                logger.debug("触发 源数据配置 onchane({} {} {})", key, prevData, curData);
                                ConfListenerFactory.onChange(source, key, prevData, curData);
                            }
                        } catch (Exception e) {
                            logger.error("节点数据解码异常 可能是未知编码数据 {} {}", this.path, event.getData().getPath());
                        }
                    }
                    break;
                case NODE_REMOVED:
                    if (event.getData().getPath().startsWith(this.path)) {
                        String key = event.getData().getPath().substring(this.path.length() + PATH_SEPARATOR.length());
                        String prevData = this.properties.getProperty(key);
                        if (prevData != null) {
                            String vailPrevData = this.propertyResolver.getProperty(key);
                            this.properties.remove(key);
                            String vailCurData = this.propertyResolver.getProperty(key);
                            if (!Objects.equals(vailCurData, vailPrevData)) {
                                logger.debug("触发 生效数据 onchane({} {} {})", key, vailPrevData, vailCurData);
                                ConfListenerFactory.onChange(key, vailPrevData, vailCurData);
                            }

                            logger.debug("触发 源数据配置({} {} {})", key, prevData, null);
                            ConfListenerFactory.onChange(source, key, prevData, null);
                        }
                    }
                    break;
                default:
                    logger.debug("未处理事件 {}", event.getType().name());
                    break;
            }
        }
    }
}
