package com.laputa.foundation.configurer.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by JiangDongPing on 2018/03/26.
 */
public class PrepPropertyNodeData extends PrepNodeData {

    private final String property;

    public PrepPropertyNodeData(String property, LaputaPlaceHolder laputaPlaceHolder, String beanName, ConfigurableListableBeanFactory beanFactoryToProcess) {
        super(laputaPlaceHolder, beanName, beanFactoryToProcess);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
