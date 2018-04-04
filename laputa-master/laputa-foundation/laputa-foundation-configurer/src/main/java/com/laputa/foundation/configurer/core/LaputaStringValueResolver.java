package com.laputa.foundation.configurer.core;

import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.util.StringValueResolver;

/**
 * Created by jiangdongping on 2018/3/26 0026.
 */
public class LaputaStringValueResolver implements StringValueResolver {

    protected boolean ignoreUnresolvablePlaceholders = false;
    protected ConfigurablePropertyResolver propertyResolver;
    protected boolean trimValues = false;
    protected String nullValue;

    public LaputaStringValueResolver(boolean ignoreUnresolvablePlaceholders,
                                     ConfigurablePropertyResolver propertyResolver,
                                     boolean trimValues,
                                     String nullValue) {
        this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
        this.propertyResolver = propertyResolver;
        this.trimValues = trimValues;
        this.nullValue = nullValue;
    }

    @Override
    public String resolveStringValue(String strVal) {
        /**
         * resolved 在 ${xxx} 匹配不到时,直接返回 ${xxx}
         */
        String resolved = (ignoreUnresolvablePlaceholders ?
                propertyResolver.resolvePlaceholders(strVal) :
                propertyResolver.resolveRequiredPlaceholders(strVal));
        if (trimValues) {
            resolved = resolved.trim();
        }
        /**
         * 返回 null 则认为该值应该配置为 null, spring不会接着往其他 配置器内查找了
         * 返回 非 null, spring 会接着遍历其他配置器, 如果后面的还有该属性, 则会覆盖
         */
        return (resolved.equals(nullValue) ? null : resolved);
    }
}
