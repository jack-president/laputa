package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.model.SysPermissionModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 权限  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
*/
public class SysPermissionBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysPermissionModelToSysPermissionBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysPermissionModel.class, SysPermission.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysPermissionToSysPermissionModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysPermission.class, SysPermissionModel.class, true);


    private static final SysPermissionEntityToSysPermissionModelConverter sysPermissionEntityToSysPermissionModelConverter = new SysPermissionEntityToSysPermissionModelConverter(Boolean.FALSE);
    private static final SysPermissionEntityToSysPermissionModelConverter sysPermissionEntityToSysPermissionModelEagerConverter = new SysPermissionEntityToSysPermissionModelConverter(Boolean.TRUE);

    private static final SysPermissionModelToSysPermissionEntityConverter sysPermissionModelToSysPermissionEntityConverter = new SysPermissionModelToSysPermissionEntityConverter();

    public static List<SysPermissionModel> sysPermissionEntityToSysPermissionModel(List<SysPermission> sysPermissionEntityList){
        List<SysPermissionModel> sysPermissionModelList = BeanCopyUtil.listCopy(sysPermissionEntityList,SysPermissionModel.class,sysPermissionToSysPermissionModelBeanCopier,sysPermissionEntityToSysPermissionModelConverter);
        return sysPermissionModelList;
    }

    public static SysPermissionModel sysPermissionEntityToSysPermissionModel(SysPermission sysPermission){
        SysPermissionModel sysPermissionModel = new SysPermissionModel();
        sysPermissionToSysPermissionModelBeanCopier.copy(sysPermission, sysPermissionModel, sysPermissionEntityToSysPermissionModelConverter);
        return sysPermissionModel;
    }

    public static SysPermissionModel sysPermissionEntityToSysPermissionModelEager(SysPermission sysPermission){
        SysPermissionModel sysPermissionModel = new SysPermissionModel();
        sysPermissionToSysPermissionModelBeanCopier.copy(sysPermission, sysPermissionModel, sysPermissionEntityToSysPermissionModelEagerConverter);
        return sysPermissionModel;
    }

    public static List<SysPermissionModel> sysPermissionEntityToSysPermissionModelEager(List<SysPermission> sysPermissionEntityList){
        List<SysPermissionModel> sysPermissionModelList = BeanCopyUtil.listCopy(sysPermissionEntityList,SysPermissionModel.class,sysPermissionToSysPermissionModelBeanCopier,sysPermissionEntityToSysPermissionModelEagerConverter);
        return sysPermissionModelList;
    }

    public static List<SysPermission> sysPermissionModelToSysPermissionEntity(List<SysPermissionModel> sysPermissionModelList){
        List<SysPermission> sysPermissionEntityList = BeanCopyUtil.listCopy(sysPermissionModelList,SysPermission.class,sysPermissionToSysPermissionModelBeanCopier,sysPermissionModelToSysPermissionEntityConverter);
        return sysPermissionEntityList;
    }

    public static SysPermission sysPermissionModelToSysPermissionEntity(SysPermissionModel sysPermissionModel){
        SysPermission sysPermissionEntity = new SysPermission();
        sysPermissionModelToSysPermissionBeanCopier.copy(sysPermissionModel,sysPermissionEntity,sysPermissionModelToSysPermissionEntityConverter);
        return sysPermissionEntity;
    }
}