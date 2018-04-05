package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 用户 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:06+08:00 .
*/
public class SysUserModelToSysUserEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysRoleCollection")) {
            List<Long> belongtoSysRoleCollectionIdList = (List<Long>) value;
            if (belongtoSysRoleCollectionIdList != null && belongtoSysRoleCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> belongtoSysRoleCollection = new java.util.ArrayList<>(belongtoSysRoleCollectionIdList.size());
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
            List<Long> belongtoSysUserGroupCollectionIdList = (List<Long>) value;
            if (belongtoSysUserGroupCollectionIdList != null && belongtoSysUserGroupCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection = new java.util.ArrayList<>(belongtoSysUserGroupCollectionIdList.size());
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