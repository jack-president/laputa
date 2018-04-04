package com.laputa.foundation.web.generate.converter;

import java.util.List;


/**
 * <p>
 * Hi菜单 Model to Entity Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
*/
public class SysHelloMenuModelToSysHelloMenuEntityConverter implements org.springframework.cglib.core.Converter {

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setChildren")) {
            java.util.List<java.lang.Long> childrenIdList = (java.util.List<java.lang.Long>) value;
            if (childrenIdList != null && childrenIdList.size() > 0) {
                java.util.List<com.laputa.foundation.web.generate.entity.SysHelloMenu> children = new java.util.ArrayList<>(childrenIdList.size());
                for (Long childrenId : childrenIdList) {
                    com.laputa.foundation.web.generate.entity.SysHelloMenu relationChildren = new com.laputa.foundation.web.generate.entity.SysHelloMenu();
                    relationChildren.setId(childrenId);
                    children.add(relationChildren);
                }
                return children;
            } else {
                return null;
            }
        }
        if (context.toString().equals("setBelongSysHelloPermissionCollection")) {
            java.util.List<java.lang.Long> belongSysHelloPermissionCollectionIdList = (java.util.List<java.lang.Long>) value;
            if (belongSysHelloPermissionCollectionIdList != null && belongSysHelloPermissionCollectionIdList.size() > 0) {
                java.util.Set<com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission> belongSysHelloPermissionCollection = new java.util.HashSet<>(belongSysHelloPermissionCollectionIdList.size());
                for (Long belongSysHelloPermissionCollectionId : belongSysHelloPermissionCollectionIdList) {
                    com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission relationBelongSysHelloPermissionCollection = new com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission();
                    relationBelongSysHelloPermissionCollection.setSysHelloPermissionId(belongSysHelloPermissionCollectionId);
                    belongSysHelloPermissionCollection.add(relationBelongSysHelloPermissionCollection);
                }
                return belongSysHelloPermissionCollection;
            } else {
                return null;
            }
        }
        return value;
    }
}