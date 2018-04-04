package com.laputa.foundation.configurer.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.MutablePropertySources;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public abstract class PrepNodeData {

    private final LaputaPlaceHolder laputaPlaceHolder;
    private final String beanName;
    private final ConfigurableListableBeanFactory beanFactoryToProcess;

    protected PrepNodeData(LaputaPlaceHolder laputaPlaceHolder,
                           String beanName,
                           ConfigurableListableBeanFactory beanFactoryToProcess) {
        this.laputaPlaceHolder = laputaPlaceHolder;
        this.beanName = beanName;
        this.beanFactoryToProcess = beanFactoryToProcess;
    }

    public LaputaPlaceHolder getLaputaPlaceHolder() {
        return laputaPlaceHolder;
    }

    public ConfigurableListableBeanFactory getBeanFactoryToProcess() {
        return beanFactoryToProcess;
    }

    public String getBeanName() {
        return beanName;
    }
}
