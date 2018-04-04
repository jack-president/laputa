package com.laputa.foundation.web.generate.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 实体表 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
*/
public class SysFieldEntityToSysFieldModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysFieldEntityToSysFieldModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}