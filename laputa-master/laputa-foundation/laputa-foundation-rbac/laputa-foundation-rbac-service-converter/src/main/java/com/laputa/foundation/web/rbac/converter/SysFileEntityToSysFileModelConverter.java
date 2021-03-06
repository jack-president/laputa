package com.laputa.foundation.web.rbac.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 系统文件 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:04+08:00 .
*/
public class SysFileEntityToSysFileModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysFileEntityToSysFileModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setBelongtoSysPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission> belongtoSysPermissionCollection = (java.util.Collection<com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission>) value;
	           if (belongtoSysPermissionCollection != null && belongtoSysPermissionCollection.size() > 0) {
	               List<Long> belongtoSysPermissionCollectionIdList = new java.util.ArrayList<Long>(belongtoSysPermissionCollection.size());
	               for (com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission relationBelongtoSysPermissionCollection: belongtoSysPermissionCollection) {
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