package com.laputa.fates.web.converter;

import java.util.List;


/**
 * <p>
 * Laputa项目 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
*/
public class LaputaAplicationModelToLaputaAplicationEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setConfigList")) {
            java.util.List<java.lang.Long> configListIdList = (java.util.List<java.lang.Long>) value;
            if (configListIdList != null && configListIdList.size() > 0) {
                java.util.List<com.laputa.fates.web.entity.LaputaConfig> configList = new java.util.ArrayList<>(configListIdList.size());
                for (Long configListId : configListIdList) {
                    com.laputa.fates.web.entity.LaputaConfig relationConfigList = new com.laputa.fates.web.entity.LaputaConfig();
                    relationConfigList.setId(configListId);
                    configList.add(relationConfigList);
                }
                return configList;
            } else {
                return null;
            }
        }
        return value;
    }
}