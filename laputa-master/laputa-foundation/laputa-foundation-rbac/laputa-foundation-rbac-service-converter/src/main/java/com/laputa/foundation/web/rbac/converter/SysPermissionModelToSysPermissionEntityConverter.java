package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 权限 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:05+08:00 .
*/
public class SysPermissionModelToSysPermissionEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysElementCollection")) {
            List<Long> ownSysElementCollectionIdList = (List<Long>) value;
            if (ownSysElementCollectionIdList != null && ownSysElementCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission> ownSysElementCollection = new java.util.ArrayList<>(ownSysElementCollectionIdList.size());
                for (Long ownSysElementCollectionId : ownSysElementCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission relationOwnSysElementCollection = new com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission();
                    relationOwnSysElementCollection.setSysElementId(ownSysElementCollectionId);
                    ownSysElementCollection.add(relationOwnSysElementCollection);
                }
                return ownSysElementCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setOwnSysFileCollection")) {
            List<Long> ownSysFileCollectionIdList = (List<Long>) value;
            if (ownSysFileCollectionIdList != null && ownSysFileCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission> ownSysFileCollection = new java.util.ArrayList<>(ownSysFileCollectionIdList.size());
                for (Long ownSysFileCollectionId : ownSysFileCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission relationOwnSysFileCollection = new com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission();
                    relationOwnSysFileCollection.setSysFileId(ownSysFileCollectionId);
                    ownSysFileCollection.add(relationOwnSysFileCollection);
                }
                return ownSysFileCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setOwnSysMenuCollection")) {
            List<Long> ownSysMenuCollectionIdList = (List<Long>) value;
            if (ownSysMenuCollectionIdList != null && ownSysMenuCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission> ownSysMenuCollection = new java.util.ArrayList<>(ownSysMenuCollectionIdList.size());
                for (Long ownSysMenuCollectionId : ownSysMenuCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission relationOwnSysMenuCollection = new com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission();
                    relationOwnSysMenuCollection.setSysMenuId(ownSysMenuCollectionId);
                    ownSysMenuCollection.add(relationOwnSysMenuCollection);
                }
                return ownSysMenuCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setOwnSysOperationCollection")) {
            List<Long> ownSysOperationCollectionIdList = (List<Long>) value;
            if (ownSysOperationCollectionIdList != null && ownSysOperationCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission> ownSysOperationCollection = new java.util.ArrayList<>(ownSysOperationCollectionIdList.size());
                for (Long ownSysOperationCollectionId : ownSysOperationCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission relationOwnSysOperationCollection = new com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission();
                    relationOwnSysOperationCollection.setSysOperationId(ownSysOperationCollectionId);
                    ownSysOperationCollection.add(relationOwnSysOperationCollection);
                }
                return ownSysOperationCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setBelongtoSysRoleCollection")) {
            List<Long> belongtoSysRoleCollectionIdList = (List<Long>) value;
            if (belongtoSysRoleCollectionIdList != null && belongtoSysRoleCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole> belongtoSysRoleCollection = new java.util.ArrayList<>(belongtoSysRoleCollectionIdList.size());
                for (Long belongtoSysRoleCollectionId : belongtoSysRoleCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole relationBelongtoSysRoleCollection = new com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole();
                    relationBelongtoSysRoleCollection.setSysRoleId(belongtoSysRoleCollectionId);
                    belongtoSysRoleCollection.add(relationBelongtoSysRoleCollection);
                }
                return belongtoSysRoleCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}