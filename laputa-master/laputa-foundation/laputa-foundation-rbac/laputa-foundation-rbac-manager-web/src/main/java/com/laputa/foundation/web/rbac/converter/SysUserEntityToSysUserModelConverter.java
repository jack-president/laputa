package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 用户 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
*/
public class SysUserEntityToSysUserModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysUserEntityToSysUserModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysRoleCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole> belongtoSysRoleCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole>) value;
	           if (belongtoSysRoleCollection != null && belongtoSysRoleCollection.size() > 0) {
	               java.util.List<java.lang.Long> belongtoSysRoleCollectionIdList = new java.util.ArrayList<java.lang.Long>(belongtoSysRoleCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole relationBelongtoSysRoleCollection: belongtoSysRoleCollection) {
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
        if (context.toString().equals("setBelongtoSysUserGroupCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup> belongtoSysUserGroupCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup>) value;
	           if (belongtoSysUserGroupCollection != null && belongtoSysUserGroupCollection.size() > 0) {
	               java.util.List<java.lang.Long> belongtoSysUserGroupCollectionIdList = new java.util.ArrayList<java.lang.Long>(belongtoSysUserGroupCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup relationBelongtoSysUserGroupCollection: belongtoSysUserGroupCollection) {
	                   if (relationBelongtoSysUserGroupCollection != null) {
	                       belongtoSysUserGroupCollectionIdList.add(relationBelongtoSysUserGroupCollection.getSysUserGroupId());
	                   }
	               }
	               return belongtoSysUserGroupCollectionIdList;
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