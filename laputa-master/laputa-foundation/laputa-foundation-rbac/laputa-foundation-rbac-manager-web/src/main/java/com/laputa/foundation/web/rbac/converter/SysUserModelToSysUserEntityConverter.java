package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 用户 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
*/
public class SysUserModelToSysUserEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysRoleCollection")) {
            java.util.List<java.lang.Long> belongtoSysRoleCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (belongtoSysRoleCollectionIdList != null && belongtoSysRoleCollectionIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> belongtoSysRoleCollection = new java.util.ArrayList<>(belongtoSysRoleCollectionIdList.size());
                for (Long belongtoSysRoleCollectionId : belongtoSysRoleCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole relationBelongtoSysRoleCollection = new com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole();
                    relationBelongtoSysRoleCollection.setSysRoleId(belongtoSysRoleCollectionId);
                    belongtoSysRoleCollection.add(relationBelongtoSysRoleCollection);
                }
                return belongtoSysRoleCollection;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setBelongtoSysUserGroupCollection")) {
            java.util.List<java.lang.Long> belongtoSysUserGroupCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (belongtoSysUserGroupCollectionIdList != null && belongtoSysUserGroupCollectionIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection = new java.util.ArrayList<>(belongtoSysUserGroupCollectionIdList.size());
                for (Long belongtoSysUserGroupCollectionId : belongtoSysUserGroupCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup relationBelongtoSysUserGroupCollection = new com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup();
                    relationBelongtoSysUserGroupCollection.setSysUserGroupId(belongtoSysUserGroupCollectionId);
                    belongtoSysUserGroupCollection.add(relationBelongtoSysUserGroupCollection);
                }
                return belongtoSysUserGroupCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}