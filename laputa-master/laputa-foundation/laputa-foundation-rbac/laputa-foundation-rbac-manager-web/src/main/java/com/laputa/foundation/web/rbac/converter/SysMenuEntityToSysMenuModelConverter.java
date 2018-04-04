package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 菜单 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
*/
public class SysMenuEntityToSysMenuModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysMenuEntityToSysMenuModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setChildren")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenu> children = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenu>) value;
	           if (children != null && children.size() > 0) {
	               java.util.List<java.lang.Long> childrenIdList = new java.util.ArrayList<java.lang.Long>(children.size());
	               for (com.laputa.foundation.web.rbac.entity.SysMenu relationChildren: children) {
	                   if (relationChildren != null) {
	                       childrenIdList.add(relationChildren.getId());
	                   }
	               }
	               return childrenIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setBelongtoSysPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission> belongtoSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission>) value;
	           if (belongtoSysPermissionCollection != null && belongtoSysPermissionCollection.size() > 0) {
	               java.util.List<java.lang.Long> belongtoSysPermissionCollectionIdList = new java.util.ArrayList<java.lang.Long>(belongtoSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission relationBelongtoSysPermissionCollection: belongtoSysPermissionCollection) {
	                   if (relationBelongtoSysPermissionCollection != null) {
	                       belongtoSysPermissionCollectionIdList.add(relationBelongtoSysPermissionCollection.getSysPermissionId());
	                   }
	               }
	               return belongtoSysPermissionCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        return value;
    }
}