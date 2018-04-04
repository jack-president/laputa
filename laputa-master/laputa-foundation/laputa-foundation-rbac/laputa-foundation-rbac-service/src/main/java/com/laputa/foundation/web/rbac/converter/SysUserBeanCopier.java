package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.model.SysUserModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 用户  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-03T11:41:06+08:00 .
*/
public class SysUserBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysUserModelToSysUserBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysUserModel.class, SysUser.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysUserToSysUserModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysUser.class, SysUserModel.class, true);


    private static final SysUserEntityToSysUserModelConverter sysUserEntityToSysUserModelConverter = new SysUserEntityToSysUserModelConverter(Boolean.FALSE);
    private static final SysUserEntityToSysUserModelConverter sysUserEntityToSysUserModelEagerConverter = new SysUserEntityToSysUserModelConverter(Boolean.TRUE);

    private static final SysUserModelToSysUserEntityConverter sysUserModelToSysUserEntityConverter = new SysUserModelToSysUserEntityConverter();

    public static List<SysUserModel> sysUserEntityToSysUserModel(List<SysUser> sysUserEntityList){
        List<SysUserModel> sysUserModelList = BeanCopyUtil.listCopy(sysUserEntityList,SysUserModel.class,sysUserToSysUserModelBeanCopier,sysUserEntityToSysUserModelConverter);
        return sysUserModelList;
    }

    public static SysUserModel sysUserEntityToSysUserModel(SysUser sysUser){
        SysUserModel sysUserModel = new SysUserModel();
        sysUserToSysUserModelBeanCopier.copy(sysUser, sysUserModel, sysUserEntityToSysUserModelConverter);
        return sysUserModel;
    }

    public static SysUserModel sysUserEntityToSysUserModelEager(SysUser sysUser){
        SysUserModel sysUserModel = new SysUserModel();
        sysUserToSysUserModelBeanCopier.copy(sysUser, sysUserModel, sysUserEntityToSysUserModelEagerConverter);
        return sysUserModel;
    }

    public static List<SysUserModel> sysUserEntityToSysUserModelEager(List<SysUser> sysUserEntityList){
        List<SysUserModel> sysUserModelList = BeanCopyUtil.listCopy(sysUserEntityList,SysUserModel.class,sysUserToSysUserModelBeanCopier,sysUserEntityToSysUserModelEagerConverter);
        return sysUserModelList;
    }

    public static List<SysUser> sysUserModelToSysUserEntity(List<SysUserModel> sysUserModelList){
        List<SysUser> sysUserEntityList = BeanCopyUtil.listCopy(sysUserModelList,SysUser.class,sysUserModelToSysUserBeanCopier,sysUserModelToSysUserEntityConverter);
        return sysUserEntityList;
    }

    public static SysUser sysUserModelToSysUserEntity(SysUserModel sysUserModel){
        SysUser sysUserEntity = new SysUser();
        sysUserModelToSysUserBeanCopier.copy(sysUserModel,sysUserEntity,sysUserModelToSysUserEntityConverter);
        return sysUserEntity;
    }
}