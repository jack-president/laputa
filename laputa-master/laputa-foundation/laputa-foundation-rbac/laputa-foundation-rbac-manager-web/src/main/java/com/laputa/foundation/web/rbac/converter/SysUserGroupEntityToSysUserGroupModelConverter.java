package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 用户组 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:30+08:00 .
*/
public class SysUserGroupEntityToSysUserGroupModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysUserGroupEntityToSysUserGroupModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysRoleCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysRole> ownSysRoleCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysRole>) value;
	           if (ownSysRoleCollection != null && ownSysRoleCollection.size() > 0) {
	               java.util.List<java.lang.Long> ownSysRoleCollectionIdList = new java.util.ArrayList<java.lang.Long>(ownSysRoleCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysRole relationOwnSysRoleCollection: ownSysRoleCollection) {
	                   if (relationOwnSysRoleCollection != null) {
	                       ownSysRoleCollectionIdList.add(relationOwnSysRoleCollection.getId());
	                   }
	               }
	               return ownSysRoleCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setCanhaveRelationSysPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission> canhaveRelationSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission>) value;
	           if (canhaveRelationSysPermissionCollection != null && canhaveRelationSysPermissionCollection.size() > 0) {
	               java.util.List<java.lang.Long> canhaveRelationSysPermissionCollectionIdList = new java.util.ArrayList<java.lang.Long>(canhaveRelationSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission relationCanhaveRelationSysPermissionCollection: canhaveRelationSysPermissionCollection) {
	                   if (relationCanhaveRelationSysPermissionCollection != null) {
	                       canhaveRelationSysPermissionCollectionIdList.add(relationCanhaveRelationSysPermissionCollection.getSysPermissionId());
	                   }
	               }
	               return canhaveRelationSysPermissionCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setOwnSysUserCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup> ownSysUserCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup>) value;
	           if (ownSysUserCollection != null && ownSysUserCollection.size() > 0) {
	               java.util.List<java.lang.Long> ownSysUserCollectionIdList = new java.util.ArrayList<java.lang.Long>(ownSysUserCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup relationOwnSysUserCollection: ownSysUserCollection) {
	                   if (relationOwnSysUserCollection != null) {
	                       ownSysUserCollectionIdList.add(relationOwnSysUserCollection.getSysUserId());
	                   }
	               }
	               return ownSysUserCollectionIdList;
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