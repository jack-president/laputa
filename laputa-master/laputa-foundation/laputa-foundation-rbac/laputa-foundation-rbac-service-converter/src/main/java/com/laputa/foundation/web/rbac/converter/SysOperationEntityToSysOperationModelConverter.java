package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 功能操作 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:05+08:00 .
*/
public class SysOperationEntityToSysOperationModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysOperationEntityToSysOperationModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setChildren")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperation> children = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperation>) value;
	           if (children != null && children.size() > 0) {
	               List<Long> childrenIdList = new java.util.ArrayList<Long>(children.size());
	               for (com.laputa.foundation.web.rbac.entity.SysOperation relationChildren: children) {
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
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission> belongtoSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission>) value;
	           if (belongtoSysPermissionCollection != null && belongtoSysPermissionCollection.size() > 0) {
	               List<Long> belongtoSysPermissionCollectionIdList = new java.util.ArrayList<Long>(belongtoSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission relationBelongtoSysPermissionCollection: belongtoSysPermissionCollection) {
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