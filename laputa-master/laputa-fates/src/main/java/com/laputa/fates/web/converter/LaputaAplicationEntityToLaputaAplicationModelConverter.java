package com.laputa.fates.web.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
*/
public class LaputaAplicationEntityToLaputaAplicationModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public LaputaAplicationEntityToLaputaAplicationModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setConfigList")) {
            if(eager){
	           java.util.Collection<com.laputa.fates.web.entity.LaputaConfig> configList = (java.util.Collection<com.laputa.fates.web.entity.LaputaConfig>) value;
	           if (configList != null && configList.size() > 0) {
	               java.util.List<java.lang.Long> configListIdList = new java.util.ArrayList<java.lang.Long>(configList.size());
	               for (com.laputa.fates.web.entity.LaputaConfig relationConfigList: configList) {
	                   if (relationConfigList != null) {
	                       configListIdList.add(relationConfigList.getId());
	                   }
	               }
	               return configListIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        return value;
    }
}