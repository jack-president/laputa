package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import com.laputa.foundation.configurer.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.env.MutablePropertySources;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public final class InjectByMethodNode extends CloudConfigBeanNode {

    private static Logger logger = LoggerFactory.getLogger(InjectByMethodNode.class);

    private final Method method;

    private final Class injectTargetClass;


    public InjectByMethodNode(String beanName, Object targetBean, Method method, String defaultVaule) {
        super(beanName, targetBean, defaultVaule);
        this.method = method;
        this.injectTargetClass = ReflectUtil.analyTargetClass(method);
    }

    @Override
    public final void injectValue(final String value) {

        boolean accessible = method.isAccessible();
        if (!accessible) {
            method.setAccessible(true);
        }

        try {
            method.invoke(targetBean, toInjectTarget(value, injectTargetClass));
        } catch (Exception e) {
            logger.error("{} {}  通过 Method 注入值异常", this.beanName, value);
            LaputaConfigurerException.ExceptionEnum.METHOD_INJECT_FAIL
                    .generateException("{0} {1} 通过 Method 注入值异常", this.beanName, value);
        }


        if (!accessible) {
            method.setAccessible(false);
        }
    }
}
