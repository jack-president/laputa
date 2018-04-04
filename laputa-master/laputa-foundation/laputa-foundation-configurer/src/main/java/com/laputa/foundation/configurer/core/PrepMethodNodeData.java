package com.laputa.foundation.configurer.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Method;

/**
 * Created by JiangDongPing on 2018/03/26.
 */
public class PrepMethodNodeData extends PrepNodeData {

    private final Method method;

    public PrepMethodNodeData(Method method, LaputaPlaceHolder laputaPlaceHolder,
                              String beanName,
                              ConfigurableListableBeanFactory beanFactoryToProcess) {
        super(laputaPlaceHolder, beanName, beanFactoryToProcess);
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }
}
