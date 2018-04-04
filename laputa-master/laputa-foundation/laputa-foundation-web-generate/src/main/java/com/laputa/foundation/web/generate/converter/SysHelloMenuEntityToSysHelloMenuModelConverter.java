package com.laputa.foundation.web.generate.converter;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi菜单 Entity to Model Converter<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
*/
public class SysHelloMenuEntityToSysHelloMenuModelConverter implements org.springframework.cglib.core.Converter {

	private Boolean eager;

	public SysHelloMenuEntityToSysHelloMenuModelConverter(Boolean eager){
		this.eager = eager;
	}

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public Object convert(Object value, Class target, Object context) {
        if (context.toString().equals("setChildren")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.generate.entity.SysHelloMenu> children = (java.util.Collection<com.laputa.foundation.web.generate.entity.SysHelloMenu>) value;
	           if (children != null && children.size() > 0) {
	               java.util.List<java.lang.Long> childrenIdList = new java.util.ArrayList<java.lang.Long>(children.size());
	               for (com.laputa.foundation.web.generate.entity.SysHelloMenu relationChildren: children) {
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
        if (context.toString().equals("setBelongSysHelloPermissionCollection")) {
            if(eager){
	           java.util.Collection<com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission> belongSysHelloPermissionCollection = (java.util.Collection<com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission>) value;
	           if (belongSysHelloPermissionCollection != null && belongSysHelloPermissionCollection.size() > 0) {
	               java.util.List<java.lang.Long> belongSysHelloPermissionCollectionIdList = new java.util.ArrayList<java.lang.Long>(belongSysHelloPermissionCollection.size());
	               for (com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission relationBelongSysHelloPermissionCollection: belongSysHelloPermissionCollection) {
	                   if (relationBelongSysHelloPermissionCollection != null) {
	                       belongSysHelloPermissionCollectionIdList.add(relationBelongSysHelloPermissionCollection.getSysHelloPermissionId());
	                   }
	               }
	               return belongSysHelloPermissionCollectionIdList;
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