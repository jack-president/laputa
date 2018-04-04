package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MutablePropertySources;

import java.lang.reflect.Field;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class InjectByFileldNode extends CloudConfigBeanNode {

    private static Logger logger = LoggerFactory.getLogger(InjectByFileldNode.class);

    private Field fieldItem;


    public InjectByFileldNode(String beanName, Object targetBean, Field fieldItem) {
        super(beanName, targetBean);
        this.fieldItem = fieldItem;
    }

    @Override
    public void injectValue(String value) {
        boolean accessible = fieldItem.isAccessible();
        if (!accessible) {
            fieldItem.setAccessible(true);
        }

        try {
            fieldItem.set(targetBean, value);
        } catch (IllegalAccessException e) {
            logger.error("{0} {1} {2} 通过 Field 注入值异常", this.beanName, fieldItem.getName(), value);
            LaputaConfigurerException.ExceptionEnum.FIELD_INJECT_FAIL
                    .generateException("{0} {1} {2} 通过 Field 注入值异常", this.beanName, fieldItem.getName(), value);
        }

        if (!accessible) {
            fieldItem.setAccessible(false);
        }
    }
}
