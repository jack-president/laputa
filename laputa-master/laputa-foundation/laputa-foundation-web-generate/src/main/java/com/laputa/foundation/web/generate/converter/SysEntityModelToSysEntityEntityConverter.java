package com.laputa.foundation.web.generate.converter;

import java.util.List;


/**
 * <p>
 * 实体表 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-16T15:50:48+08:00 .
*/
public class SysEntityModelToSysEntityEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setSysFieldCollection")) {
            List<Long> sysFieldCollectionIdList = (List<Long>) value;
            if (sysFieldCollectionIdList != null && sysFieldCollectionIdList.size() > 0) {
                java.util.Set<com.laputa.foundation.web.generate.entity.SysField> sysFieldCollection = new java.util.HashSet<com.laputa.foundation.web.generate.entity.SysField>(sysFieldCollectionIdList.size());
                for (Long sysFieldCollectionId : sysFieldCollectionIdList) {
                    com.laputa.foundation.web.generate.entity.SysField relationSysFieldCollection = new com.laputa.foundation.web.generate.entity.SysField();
                    relationSysFieldCollection.setId(sysFieldCollectionId);
                    sysFieldCollection.add(relationSysFieldCollection);
                }
                return sysFieldCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}