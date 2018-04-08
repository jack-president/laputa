package com.laputa.fates.web.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:11+08:00 .
*/
public class LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        return value;
    }
}