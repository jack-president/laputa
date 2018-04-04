package com.laputa.foundation.web.weixin.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 微信公众号基础配置 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
*/
public class WeixinBaseConfigEntityToWeixinBaseConfigModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public WeixinBaseConfigEntityToWeixinBaseConfigModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}