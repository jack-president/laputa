package com.laputa.foundation.web.generate.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi权限 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-05T11:02:51+08:00 .
*/
public class SysHelloPermissionEntityToSysHelloPermissionModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysHelloPermissionEntityToSysHelloPermissionModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}