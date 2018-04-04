package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 角色 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:05+08:00 .
*/
public class SysRoleModelToSysRoleEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysPermissionCollection")) {
            List<Long> ownSysPermissionCollectionIdList = (List<Long>) value;
            if (ownSysPermissionCollectionIdList != null && ownSysPermissionCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole> ownSysPermissionCollection = new java.util.ArrayList<>(ownSysPermissionCollectionIdList.size());
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
            List<Long> ownSysUserCollectionIdList = (List<Long>) value;
            if (ownSysUserCollectionIdList != null && ownSysUserCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> ownSysUserCollection = new java.util.ArrayList<>(ownSysUserCollectionIdList.size());
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