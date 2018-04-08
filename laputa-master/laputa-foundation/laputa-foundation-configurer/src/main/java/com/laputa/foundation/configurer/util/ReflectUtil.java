package com.laputa.foundation.configurer.util;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by JiangDongPing on 2018/04/07.
 */
public class ReflectUtil {

    public static Class analyTargetClass(Method method) {
        if (method.getParameterTypes() != null && method.getParameterTypes().length > 0) {
            return method.getParameterTypes()[0];
        } else {
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException("{0} 注入方法 {1} 无参数", method.getDeclaringClass().getName(), method.getName());
        }
    }

    public static Class analyTargetClass(Field field) {
        return field.getType();
    }
}
