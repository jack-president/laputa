package com.laputa.foundation.configurer.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Field;

/**
 * Created by JiangDongPing on 2018/03/26.
 */
public class PrepFieldNodeData extends PrepNodeData {

    private final Field field;

    public PrepFieldNodeData(Field field, LaputaPlaceHolder laputaPlaceHolder,
                             String beanName,
                             ConfigurableListableBeanFactory beanFactoryToProcess) {
        super(laputaPlaceHolder, beanName, beanFactoryToProcess);
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
