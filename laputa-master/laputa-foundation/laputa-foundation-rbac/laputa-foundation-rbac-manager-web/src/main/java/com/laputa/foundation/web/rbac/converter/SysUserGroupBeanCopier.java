package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.model.SysUserGroupModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 用户组  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-08-15T09:50:30+08:00 .
*/
public class SysUserGroupBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysUserGroupModelToSysUserGroupBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysUserGroupModel.class, SysUserGroup.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysUserGroupToSysUserGroupModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysUserGroup.class, SysUserGroupModel.class, true);


    private static final SysUserGroupEntityToSysUserGroupModelConverter sysUserGroupEntityToSysUserGroupModelConverter = new SysUserGroupEntityToSysUserGroupModelConverter(Boolean.FALSE);
    private static final SysUserGroupEntityToSysUserGroupModelConverter sysUserGroupEntityToSysUserGroupModelEagerConverter = new SysUserGroupEntityToSysUserGroupModelConverter(Boolean.TRUE);

    private static final SysUserGroupModelToSysUserGroupEntityConverter sysUserGroupModelToSysUserGroupEntityConverter = new SysUserGroupModelToSysUserGroupEntityConverter();

    public static List<SysUserGroupModel> sysUserGroupEntityToSysUserGroupModel(List<SysUserGroup> sysUserGroupEntityList){
        List<SysUserGroupModel> sysUserGroupModelList = BeanCopyUtil.listCopy(sysUserGroupEntityList,SysUserGroupModel.class,sysUserGroupToSysUserGroupModelBeanCopier,sysUserGroupEntityToSysUserGroupModelConverter);
        return sysUserGroupModelList;
    }

    public static SysUserGroupModel sysUserGroupEntityToSysUserGroupModel(SysUserGroup sysUserGroup){
        SysUserGroupModel sysUserGroupModel = new SysUserGroupModel();
        sysUserGroupToSysUserGroupModelBeanCopier.copy(sysUserGroup, sysUserGroupModel, sysUserGroupEntityToSysUserGroupModelConverter);
        return sysUserGroupModel;
    }

    public static SysUserGroupModel sysUserGroupEntityToSysUserGroupModelEager(SysUserGroup sysUserGroup){
        SysUserGroupModel sysUserGroupModel = new SysUserGroupModel();
        sysUserGroupToSysUserGroupModelBeanCopier.copy(sysUserGroup, sysUserGroupModel, sysUserGroupEntityToSysUserGroupModelEagerConverter);
        return sysUserGroupModel;
    }

    public static List<SysUserGroupModel> sysUserGroupEntityToSysUserGroupModelEager(List<SysUserGroup> sysUserGroupEntityList){
        List<SysUserGroupModel> sysUserGroupModelList = BeanCopyUtil.listCopy(sysUserGroupEntityList,SysUserGroupModel.class,sysUserGroupToSysUserGroupModelBeanCopier,sysUserGroupEntityToSysUserGroupModelEagerConverter);
        return sysUserGroupModelList;
    }

    public static List<SysUserGroup> sysUserGroupModelToSysUserGroupEntity(List<SysUserGroupModel> sysUserGroupModelList){
        List<SysUserGroup> sysUserGroupEntityList = BeanCopyUtil.listCopy(sysUserGroupModelList,SysUserGroup.class,sysUserGroupToSysUserGroupModelBeanCopier,sysUserGroupModelToSysUserGroupEntityConverter);
        return sysUserGroupEntityList;
    }

    public static SysUserGroup sysUserGroupModelToSysUserGroupEntity(SysUserGroupModel sysUserGroupModel){
        SysUserGroup sysUserGroupEntity = new SysUserGroup();
        sysUserGroupModelToSysUserGroupBeanCopier.copy(sysUserGroupModel,sysUserGroupEntity,sysUserGroupModelToSysUserGroupEntityConverter);
        return sysUserGroupEntity;
    }
}