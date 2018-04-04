package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 系统元素 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:03+08:00 .
*/
public class SysElementEntityToSysElementModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysElementEntityToSysElementModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission> belongtoSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission>) value;
	           if (belongtoSysPermissionCollection != null && belongtoSysPermissionCollection.size() > 0) {
	               List<Long> belongtoSysPermissionCollectionIdList = new java.util.ArrayList<Long>(belongtoSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission relationBelongtoSysPermissionCollection: belongtoSysPermissionCollection) {
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