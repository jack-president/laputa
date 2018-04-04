package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 角色 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:29+08:00 .
*/
public class SysRoleModelToSysRoleEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysPermissionCollection")) {
            java.util.List<java.lang.Long> ownSysPermissionCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (ownSysPermissionCollectionIdList != null && ownSysPermissionCollectionIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole> ownSysPermissionCollection = new java.util.ArrayList<>(ownSysPermissionCollectionIdList.size());
                for (Long ownSysPermissionCollectionId : ownSysPermissionCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole relationOwnSysPermissionCollection = new com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole();
                    relationOwnSysPermissionCollection.setSysPermissionId(ownSysPermissionCollectionId);
                    ownSysPermissionCollection.add(relationOwnSysPermissionCollection);
                }
                return ownSysPermissionCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setOwnSysUserCollection")) {
            java.util.List<java.lang.Long> ownSysUserCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (ownSysUserCollectionIdList != null && ownSysUserCollectionIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> ownSysUserCollection = new java.util.ArrayList<>(ownSysUserCollectionIdList.size());
                for (Long ownSysUserCollectionId : ownSysUserCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole relationOwnSysUserCollection = new com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole();
                    relationOwnSysUserCollection.setSysUserId(ownSysUserCollectionId);
                    ownSysUserCollection.add(relationOwnSysUserCollection);
                }
                return ownSysUserCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}