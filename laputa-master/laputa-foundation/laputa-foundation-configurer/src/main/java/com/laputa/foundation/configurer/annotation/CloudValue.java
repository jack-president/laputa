package com.laputa.foundation.configurer.annotation;


import java.lang.annotation.*;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CloudValue {

    /**
     * conf key
     *
     * @return
     */
    String value();

    /**
     * conf default value
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * whether you need a callback refresh, when the value changes.
     *
     * @return
     */
    boolean callback() default true;
}
