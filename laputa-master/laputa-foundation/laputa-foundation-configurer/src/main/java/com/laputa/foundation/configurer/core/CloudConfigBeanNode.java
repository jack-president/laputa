package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.listener.ConfListener;
import com.laputa.foundation.configurer.listener.ConfListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MutablePropertySources;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public abstract class CloudConfigBeanNode implements ConfListener {

    private static Logger logger = LoggerFactory.getLogger(CloudConfigBeanNode.class);

    protected final String beanName;
    protected final Object targetBean;

    public CloudConfigBeanNode(String beanName, Object targetBean) {
        this.beanName = beanName;
        this.targetBean = targetBean;
    }

    @Override
    public final void onChange(String key, String preValue, String curValue) {
        logger.debug("{} {} {} 值发生变化", targetBean.getClass().getName(), key, beanName);
        injectValue(curValue);
    }

    public abstract void injectValue(final String value);
}
