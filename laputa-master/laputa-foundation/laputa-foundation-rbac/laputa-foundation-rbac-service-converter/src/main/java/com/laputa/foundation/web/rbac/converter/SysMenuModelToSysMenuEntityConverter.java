package com.laputa.foundation.web.rbac.converter;

import java.util.List;


/**
 * <p>
 * 菜单 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:04+08:00 .
*/
public class SysMenuModelToSysMenuEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setChildren")) {
            List<Long> childrenIdList = (List<Long>) value;
            if (childrenIdList != null && childrenIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysMenu> children = new java.util.ArrayList<>(childrenIdList.size());
                for (Long childrenId : childrenIdList) {
                    com.laputa.foundation.web.rbac.entity.SysMenu relationChildren = new com.laputa.foundation.web.rbac.entity.SysMenu();
                    relationChildren.setId(childrenId);
                    children.add(relationChildren);
                }
                return children;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setBelongtoSysPermissionCollection")) {
            List<Long> belongtoSysPermissionCollectionIdList = (List<Long>) value;
            if (belongtoSysPermissionCollectionIdList != null && belongtoSysPermissionCollectionIdList.size() > 0) {
                List<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission> belongtoSysPermissionCollection = new java.util.ArrayList<>(belongtoSysPermissionCollectionIdList.size());
                for (Long belongtoSysPermissionCollectionId : belongtoSysPermissionCollectionIdList) {
                    com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission relationBelongtoSysPermissionCollection = new com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission();
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