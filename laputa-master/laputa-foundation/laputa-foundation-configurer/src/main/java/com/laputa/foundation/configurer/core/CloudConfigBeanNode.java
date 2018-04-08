package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
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
    protected final String defaultVaule;

    public CloudConfigBeanNode(String beanName, Object targetBean, String defaultVaule) {
        this.beanName = beanName;
        this.targetBean = targetBean;
        this.defaultVaule = defaultVaule;
    }

    @Override
    public final void onChange(String key, String preValue, String curValue) {
        logger.debug("{} {} {} 值发生变化", targetBean.getClass().getName(), key, beanName);
        if (curValue != null) {
            injectValue(curValue);
        } else {
            injectValue(defaultVaule);
        }
    }


    protected Object toInjectTarget(String value, Class injectTargetClass) {
        if (value == null) {
            return null;
        }
        Object result = null;
        try {
            if (String.class.equals(injectTargetClass)) {
                result = value;
            } else if (Long.class.equals(injectTargetClass)) {
                result = Long.valueOf(value);
            } else if (long.class.equals(injectTargetClass)) {
                result = Long.parseLong(value);
            } else if (Double.class.equals(injectTargetClass)) {
                result = Double.valueOf(value);
            } else if (double.class.equals(injectTargetClass)) {
                result = Double.parseDouble(value);
            } else if (Float.class.equals(injectTargetClass)) {
                result = Float.valueOf(value);
            } else if (float.class.equals(injectTargetClass)) {
                result = Float.parseFloat(value);
            } else if (Boolean.class.equals(injectTargetClass)) {
                checkBool(value);
                result = Boolean.valueOf(value);
            } else if (boolean.class.equals(injectTargetClass)) {
                checkBool(value);
                result = Boolean.valueOf(value);
            } else if (Integer.class.equals(injectTargetClass)) {
                result = Integer.valueOf(value);
            } else if (int.class.equals(injectTargetClass)) {
                result = Integer.parseInt(value);
            } else {
                throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                        .generateException("不支持的类型 {0} {1} ", injectTargetClass.getName(), value);
            }
        } catch (Exception e) {
            logger.error("值转换失败 {} {} {}", injectTargetClass.getName(), value, e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException("值转换失败 {0} {1} {2}", injectTargetClass.getName(), value, e);
        }

        if (result == null) {
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException("逻辑异常 {0} {1} ", injectTargetClass.getName(), value);
        }

        return result;
    }

    private void checkBool(String value) {
        if (!Boolean.TRUE.toString().equalsIgnoreCase(value)
                && !Boolean.FALSE.toString().equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("非法的 bool 值");
        }
    }

    public abstract void injectValue(final String value);
}
