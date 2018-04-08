package com.laputa.fates.web.converter;

import java.util.List;


/**
 * <p>
 * Laputa项目 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
*/
public class LaputaConfigModelToLaputaConfigEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setConfigHistoryList")) {
            java.util.List<java.lang.Long> configHistoryListIdList = (java.util.List<java.lang.Long>) value;
            if (configHistoryListIdList != null && configHistoryListIdList.size() > 0) {
                java.util.List<com.laputa.fates.web.entity.LaputaConfigHistory> configHistoryList = new java.util.ArrayList<>(configHistoryListIdList.size());
                for (Long configHistoryListId : configHistoryListIdList) {
                    com.laputa.fates.web.entity.LaputaConfigHistory relationConfigHistoryList = new com.laputa.fates.web.entity.LaputaConfigHistory();
                    relationConfigHistoryList.setId(configHistoryListId);
                    configHistoryList.add(relationConfigHistoryList);
                }
                return configHistoryList;
            } else {
                return null;
            }
        }
        return value;
    }
}