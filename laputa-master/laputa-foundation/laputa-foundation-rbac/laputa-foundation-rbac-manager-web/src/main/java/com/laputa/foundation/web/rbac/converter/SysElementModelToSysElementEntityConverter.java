package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 系统元素 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:40+08:00 .
*/
public class SysElementModelToSysElementEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysPermissionCollection")) {
            java.util.List<java.lang.Long> belongtoSysPermissionCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (belongtoSysPermissionCollectionIdList != null && belongtoSysPermissionCollectionIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission> belongtoSysPermissionCollection = new java.util.ArrayList<>(belongtoSysPermissionCollectionIdList.size());
                for (Long belongtoSysPermissionCollectionId : belongtoSysPermissionCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission relationBelongtoSysPermissionCollection = new com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission();
                    relationBelongtoSysPermissionCollection.setSysPermissionId(belongtoSysPermissionCollectionId);
                    belongtoSysPermissionCollection.add(relationBelongtoSysPermissionCollection);
                }
                return belongtoSysPermissionCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}