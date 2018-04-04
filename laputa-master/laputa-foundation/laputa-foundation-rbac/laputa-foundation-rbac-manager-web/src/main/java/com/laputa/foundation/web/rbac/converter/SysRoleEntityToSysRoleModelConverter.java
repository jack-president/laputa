package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 角色 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:29+08:00 .
*/
public class SysRoleEntityToSysRoleModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysRoleEntityToSysRoleModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setOwnSysPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole> ownSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole>) value;
	           if (ownSysPermissionCollection != null && ownSysPermissionCollection.size() > 0) {
	               java.util.List<java.lang.Long> ownSysPermissionCollectionIdList = new java.util.ArrayList<java.lang.Long>(ownSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole relationOwnSysPermissionCollection: ownSysPermissionCollection) {
	                   if (relationOwnSysPermissionCollection != null) {
	                       ownSysPermissionCollectionIdList.add(relationOwnSysPermissionCollection.getSysPermissionId());
	                   }
	               }
	               return ownSysPermissionCollectionIdList;
	           } else {
	               return null;
	           }
            }else{
            	return null;
            }
        }
        if (context.toString().equals("setOwnSysUserCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> ownSysUserCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole>) value;
	           if (ownSysUserCollection != null && ownSysUserCollection.size() > 0) {
	               java.util.List<java.lang.Long> ownSysUserCollectionIdList = new java.util.ArrayList<java.lang.Long>(ownSysUserCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole relationOwnSysUserCollection: ownSysUserCollection) {
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