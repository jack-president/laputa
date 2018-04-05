package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 权限 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:05+08:00 .
*/
public class SysPermissionEntityToSysPermissionModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysPermissionEntityToSysPermissionModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysElementCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission> ownSysElementCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission>) value;
	           if (ownSysElementCollection != null && ownSysElementCollection.size() > 0) {
	               List<Long> ownSysElementCollectionIdList = new java.util.ArrayList<Long>(ownSysElementCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission relationOwnSysElementCollection: ownSysElementCollection) {
	                   if (relationOwnSysElementCollection != null) {
	                       ownSysElementCollectionIdList.add(relationOwnSysElementCollection.getSysElementId());
	                   }
	               }
	               return ownSysElementCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setOwnSysFileCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission> ownSysFileCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission>) value;
	           if (ownSysFileCollection != null && ownSysFileCollection.size() > 0) {
	               List<Long> ownSysFileCollectionIdList = new java.util.ArrayList<Long>(ownSysFileCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission relationOwnSysFileCollection: ownSysFileCollection) {
	                   if (relationOwnSysFileCollection != null) {
	                       ownSysFileCollectionIdList.add(relationOwnSysFileCollection.getSysFileId());
	                   }
	               }
	               return ownSysFileCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setOwnSysMenuCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission> ownSysMenuCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission>) value;
	           if (ownSysMenuCollection != null && ownSysMenuCollection.size() > 0) {
	               List<Long> ownSysMenuCollectionIdList = new java.util.ArrayList<Long>(ownSysMenuCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission relationOwnSysMenuCollection: ownSysMenuCollection) {
	                   if (relationOwnSysMenuCollection != null) {
	                       ownSysMenuCollectionIdList.add(relationOwnSysMenuCollection.getSysMenuId());
	                   }
	               }
	               return ownSysMenuCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setOwnSysOperationCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission> ownSysOperationCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission>) value;
	           if (ownSysOperationCollection != null && ownSysOperationCollection.size() > 0) {
	               List<Long> ownSysOperationCollectionIdList = new java.util.ArrayList<Long>(ownSysOperationCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission relationOwnSysOperationCollection: ownSysOperationCollection) {
	                   if (relationOwnSysOperationCollection != null) {
	                       ownSysOperationCollectionIdList.add(relationOwnSysOperationCollection.getSysOperationId());
	                   }
	               }
	               return ownSysOperationCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setBelongtoSysRoleCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole> belongtoSysRoleCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole>) value;
	           if (belongtoSysRoleCollection != null && belongtoSysRoleCollection.size() > 0) {
	               List<Long> belongtoSysRoleCollectionIdList = new java.util.ArrayList<Long>(belongtoSysRoleCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole relationBelongtoSysRoleCollection: belongtoSysRoleCollection) {
	                   if (relationBelongtoSysRoleCollection != null) {
	                       belongtoSysRoleCollectionIdList.add(relationBelongtoSysRoleCollection.getSysRoleId());
	                   }
	               }
	               return belongtoSysRoleCollectionIdList;
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