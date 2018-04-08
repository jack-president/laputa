package com.laputa.fates.web.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
*/
public class LaputaConfigEntityToLaputaConfigModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public LaputaConfigEntityToLaputaConfigModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setConfigHistoryList")) {
            if(eager){
	           java.util.Collection<com.laputa.fates.web.entity.LaputaConfigHistory> configHistoryList = (java.util.Collection<com.laputa.fates.web.entity.LaputaConfigHistory>) value;
	           if (configHistoryList != null && configHistoryList.size() > 0) {
	               java.util.List<java.lang.Long> configHistoryListIdList = new java.util.ArrayList<java.lang.Long>(configHistoryList.size());
	               for (com.laputa.fates.web.entity.LaputaConfigHistory relationConfigHistoryList: configHistoryList) {
	                   if (relationConfigHistoryList != null) {
	                       configHistoryListIdList.add(relationConfigHistoryList.getId());
	                   }
	               }
	               return configHistoryListIdList;
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