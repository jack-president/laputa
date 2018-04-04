package com.laputa.foundation.web.generate.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi时间 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
*/
public class SysHelloDateEntityToSysHelloDateModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysHelloDateEntityToSysHelloDateModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}