package com.laputa.foundation.web.generate.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p/>
 * 实体表 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-16T15:50:48+08:00 .
 */
public class SysEntityEntityToSysEntityModelConverter implements org.springframework.cglib.core.Converter {

    private Boolean eager;

    public SysEntityEntityToSysEntityModelConverter(Boolean eager) {
        this.eager = eager;
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setSysFieldCollection")) {
            if (eager) {
                java.util.Collection<com.laputa.foundation.web.generate.entity.SysField> sysFieldCollection =
                        (java.util.Collection<com.laputa.foundation.web.generate.entity.SysField>) value;
                if (sysFieldCollection != null && sysFieldCollection.size() > 0) {
                    List<Long> sysFieldCollectionIdList = new java.util.ArrayList<Long>(sysFieldCollection.size());
                    for (com.laputa.foundation.web.generate.entity.SysField relationSysFieldCollection : sysFieldCollection) {
                        if (relationSysFieldCollection != null) {
                            sysFieldCollectionIdList.add(relationSysFieldCollection.getId());
                        }
                    }
                    return sysFieldCollectionIdList;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return value;
    }
}