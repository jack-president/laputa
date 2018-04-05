package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.model.SysRoleModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 角色  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:05+08:00 .
*/
public class SysRoleBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysRoleModelToSysRoleBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysRoleModel.class, SysRole.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysRoleToSysRoleModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysRole.class, SysRoleModel.class, true);


    private static final SysRoleEntityToSysRoleModelConverter sysRoleEntityToSysRoleModelConverter = new SysRoleEntityToSysRoleModelConverter(Boolean.FALSE);
    private static final SysRoleEntityToSysRoleModelConverter sysRoleEntityToSysRoleModelEagerConverter = new SysRoleEntityToSysRoleModelConverter(Boolean.TRUE);

    private static final SysRoleModelToSysRoleEntityConverter sysRoleModelToSysRoleEntityConverter = new SysRoleModelToSysRoleEntityConverter();

    public static List<SysRoleModel> sysRoleEntityToSysRoleModel(List<SysRole> sysRoleEntityList){
        List<SysRoleModel> sysRoleModelList = BeanCopyUtil.listCopy(sysRoleEntityList,SysRoleModel.class,sysRoleToSysRoleModelBeanCopier,sysRoleEntityToSysRoleModelConverter);
        return sysRoleModelList;
    }

    public static SysRoleModel sysRoleEntityToSysRoleModel(SysRole sysRole){
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleToSysRoleModelBeanCopier.copy(sysRole, sysRoleModel, sysRoleEntityToSysRoleModelConverter);
        return sysRoleModel;
    }

    public static SysRoleModel sysRoleEntityToSysRoleModelEager(SysRole sysRole){
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleToSysRoleModelBeanCopier.copy(sysRole, sysRoleModel, sysRoleEntityToSysRoleModelEagerConverter);
        return sysRoleModel;
    }

    public static List<SysRoleModel> sysRoleEntityToSysRoleModelEager(List<SysRole> sysRoleEntityList){
        List<SysRoleModel> sysRoleModelList = BeanCopyUtil.listCopy(sysRoleEntityList,SysRoleModel.class,sysRoleToSysRoleModelBeanCopier,sysRoleEntityToSysRoleModelEagerConverter);
        return sysRoleModelList;
    }

    public static List<SysRole> sysRoleModelToSysRoleEntity(List<SysRoleModel> sysRoleModelList){
        List<SysRole> sysRoleEntityList = BeanCopyUtil.listCopy(sysRoleModelList,SysRole.class,sysRoleModelToSysRoleBeanCopier,sysRoleModelToSysRoleEntityConverter);
        return sysRoleEntityList;
    }

    public static SysRole sysRoleModelToSysRoleEntity(SysRoleModel sysRoleModel){
        SysRole sysRoleEntity = new SysRole();
        sysRoleModelToSysRoleBeanCopier.copy(sysRoleModel,sysRoleEntity,sysRoleModelToSysRoleEntityConverter);
        return sysRoleEntity;
    }
}