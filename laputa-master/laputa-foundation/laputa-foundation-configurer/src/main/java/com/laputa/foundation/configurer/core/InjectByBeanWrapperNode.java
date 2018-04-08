package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import com.laputa.foundation.configurer.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * Created by JiangDongPing on 2018/03/26.
 */
public class InjectByBeanWrapperNode extends CloudConfigBeanNode {

    private static Logger logger = LoggerFactory.getLogger(CloudConfigBeanNode.class);

    private final String property;
    private final BeanWrapperImpl beanWrapper;
    private PropertyDescriptor propertyDescriptor = null;
    private Field field = null;


    public InjectByBeanWrapperNode(String beanName, Object targetBean, String property, String defaultVaule) {
        super(beanName, targetBean, defaultVaule);
        this.property = property;
        this.beanWrapper = new BeanWrapperImpl(targetBean);
        // property descriptor
        PropertyDescriptor propertyDescriptor = null;
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            for (PropertyDescriptor item : propertyDescriptors) {
                if (property.equals(item.getName())) {
                    this.propertyDescriptor = item;
                    break;
                }
            }
        }

        // refresh field: set or field
        if (propertyDescriptor == null || propertyDescriptor.getWriteMethod() == null) {
            Field[] beanFields = targetBean.getClass().getDeclaredFields();
            if (beanFields != null && beanFields.length > 0) {
                for (Field fieldItem : beanFields) {
                    if (property.equals(fieldItem.getName())) {
                        this.field = fieldItem;
                    }
                }
            }
        }

    }

    @Override
    public void injectValue(String value) {
        //默认使用方法注入,方法不存在时使用字段注入
        if (propertyDescriptor != null && propertyDescriptor.getWriteMethod() != null) {
            try {
                beanWrapper.setPropertyValue(property, value);
            } catch (Exception e) {
                logger.error("{0} {1} {2} 通过 Field 注入值异常", this.beanName, property, value);
                LaputaConfigurerException.ExceptionEnum.METHOD_INJECT_FAIL
                        .generateException("{0} {1} {2} 通过 Mehod 注入值异常", this.beanName, property, value);
            }

        } else if (this.field != null) {
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }

            try {
                field.set(targetBean, toInjectTarget(value, ReflectUtil.analyTargetClass(field)));
            } catch (Exception e) {
                logger.error("{0} {1} {2} 通过 Field 注入值异常", this.beanName, field.getName(), value);
                LaputaConfigurerException.ExceptionEnum.FIELD_INJECT_FAIL
                        .generateException("{0} {1} {2} 通过 Field 注入值异常", this.beanName, field.getName(), value);
            }

            if (!accessible) {
                field.setAccessible(false);
            }
        }
    }
}
