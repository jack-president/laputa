package com.laputa.foundation.web.generate.converter;

import com.laputa.foundation.web.generate.entity.SysHelloPermission;
import com.laputa.foundation.web.generate.model.SysHelloPermissionModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi权限  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-05T11:02:51+08:00 .
*/
public class SysHelloPermissionBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysHelloPermissionModelToSysHelloPermissionBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloPermissionModel.class, SysHelloPermission.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysHelloPermissionToSysHelloPermissionModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloPermission.class, SysHelloPermissionModel.class, true);


    private static final SysHelloPermissionEntityToSysHelloPermissionModelConverter sysHelloPermissionEntityToSysHelloPermissionModelConverter = new SysHelloPermissionEntityToSysHelloPermissionModelConverter(Boolean.FALSE);
    private static final SysHelloPermissionEntityToSysHelloPermissionModelConverter sysHelloPermissionEntityToSysHelloPermissionModelEagerConverter = new SysHelloPermissionEntityToSysHelloPermissionModelConverter(Boolean.TRUE);

    private static final SysHelloPermissionModelToSysHelloPermissionEntityConverter sysHelloPermissionModelToSysHelloPermissionEntityConverter = new SysHelloPermissionModelToSysHelloPermissionEntityConverter();

    public static List<SysHelloPermissionModel> sysHelloPermissionEntityToSysHelloPermissionModel(List<SysHelloPermission> sysHelloPermissionEntityList){
        List<SysHelloPermissionModel> sysHelloPermissionModelList = BeanCopyUtil.listCopy(sysHelloPermissionEntityList,SysHelloPermissionModel.class,sysHelloPermissionToSysHelloPermissionModelBeanCopier,sysHelloPermissionEntityToSysHelloPermissionModelConverter);
        return sysHelloPermissionModelList;
    }

    public static SysHelloPermissionModel sysHelloPermissionEntityToSysHelloPermissionModel(SysHelloPermission sysHelloPermission){
        SysHelloPermissionModel sysHelloPermissionModel = new SysHelloPermissionModel();
        sysHelloPermissionToSysHelloPermissionModelBeanCopier.copy(sysHelloPermission, sysHelloPermissionModel, sysHelloPermissionEntityToSysHelloPermissionModelConverter);
        return sysHelloPermissionModel;
    }

    public static SysHelloPermissionModel sysHelloPermissionEntityToSysHelloPermissionModelEager(SysHelloPermission sysHelloPermission){
        SysHelloPermissionModel sysHelloPermissionModel = new SysHelloPermissionModel();
        sysHelloPermissionToSysHelloPermissionModelBeanCopier.copy(sysHelloPermission, sysHelloPermissionModel, sysHelloPermissionEntityToSysHelloPermissionModelEagerConverter);
        return sysHelloPermissionModel;
    }

    public static List<SysHelloPermissionModel> sysHelloPermissionEntityToSysHelloPermissionModelEager(List<SysHelloPermission> sysHelloPermissionEntityList){
        List<SysHelloPermissionModel> sysHelloPermissionModelList = BeanCopyUtil.listCopy(sysHelloPermissionEntityList,SysHelloPermissionModel.class,sysHelloPermissionToSysHelloPermissionModelBeanCopier,sysHelloPermissionEntityToSysHelloPermissionModelEagerConverter);
        return sysHelloPermissionModelList;
    }

    public static List<SysHelloPermission> sysHelloPermissionModelToSysHelloPermissionEntity(List<SysHelloPermissionModel> sysHelloPermissionModelList){
        List<SysHelloPermission> sysHelloPermissionEntityList = BeanCopyUtil.listCopy(sysHelloPermissionModelList,SysHelloPermission.class,sysHelloPermissionToSysHelloPermissionModelBeanCopier,sysHelloPermissionModelToSysHelloPermissionEntityConverter);
        return sysHelloPermissionEntityList;
    }

    public static SysHelloPermission sysHelloPermissionModelToSysHelloPermissionEntity(SysHelloPermissionModel sysHelloPermissionModel){
        SysHelloPermission sysHelloPermissionEntity = new SysHelloPermission();
        sysHelloPermissionModelToSysHelloPermissionBeanCopier.copy(sysHelloPermissionModel,sysHelloPermissionEntity,sysHelloPermissionModelToSysHelloPermissionEntityConverter);
        return sysHelloPermissionEntity;
    }
}