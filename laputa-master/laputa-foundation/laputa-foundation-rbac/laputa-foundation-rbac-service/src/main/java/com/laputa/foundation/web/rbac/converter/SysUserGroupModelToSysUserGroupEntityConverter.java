package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 用户组 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:06+08:00 .
*/
public class SysUserGroupModelToSysUserGroupEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysRoleCollection")) {
            List<Long> ownSysRoleCollectionIdList = (List<Long>) value;
            if (ownSysRoleCollectionIdList != null && ownSysRoleCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysRole> ownSysRoleCollection = new java.util.ArrayList<>(ownSysRoleCollectionIdList.size());
                for (Long ownSysRoleCollectionId : ownSysRoleCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysRole relationOwnSysRoleCollection = new com.laputa.foundation.web.rbac.entity.SysRole();
                    relationOwnSysRoleCollection.setId(ownSysRoleCollectionId);
                    ownSysRoleCollection.add(relationOwnSysRoleCollection);
                }
                return ownSysRoleCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setCanhaveRelationSysPermissionCollection")) {
            List<Long> canhaveRelationSysPermissionCollectionIdList = (List<Long>) value;
            if (canhaveRelationSysPermissionCollectionIdList != null && canhaveRelationSysPermissionCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission> canhaveRelationSysPermissionCollection = new java.util.ArrayList<>(canhaveRelationSysPermissionCollectionIdList.size());
                for (Long canhaveRelationSysPermissionCollectionId : canhaveRelationSysPermissionCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission relationCanhaveRelationSysPermissionCollection = new com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission();
                    relationCanhaveRelationSysPermissionCollection.setSysPermissionId(canhaveRelationSysPermissionCollectionId);
                    canhaveRelationSysPermissionCollection.add(relationCanhaveRelationSysPermissionCollection);
                }
                return canhaveRelationSysPermissionCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setOwnSysUserCollection")) {
            List<Long> ownSysUserCollectionIdList = (List<Long>) value;
            if (ownSysUserCollectionIdList != null && ownSysUserCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup> ownSysUserCollection = new java.util.ArrayList<>(ownSysUserCollectionIdList.size());
                for (Long ownSysUserCollectionId : ownSysUserCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup relationOwnSysUserCollection = new com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup();
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