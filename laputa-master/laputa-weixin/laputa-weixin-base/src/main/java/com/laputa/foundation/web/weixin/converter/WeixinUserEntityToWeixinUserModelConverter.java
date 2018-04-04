package com.laputa.foundation.web.weixin.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 微信用户 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-15T17:24:50+08:00 .
*/
public class WeixinUserEntityToWeixinUserModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public WeixinUserEntityToWeixinUserModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}