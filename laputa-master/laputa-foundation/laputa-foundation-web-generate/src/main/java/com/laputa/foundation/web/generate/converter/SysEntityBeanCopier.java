package com.laputa.foundation.web.generate.converter;

import com.laputa.foundation.web.generate.entity.SysEntity;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;
import com.laputa.foundation.web.generate.model.SysEntityModel;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 实体表  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-16T15:50:48+08:00 .
*/
public class SysEntityBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysEntityModelToSysEntityBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysEntityModel.class, SysEntity.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysEntityToSysEntityModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysEntity.class, SysEntityModel.class, true);


    private static final SysEntityEntityToSysEntityModelConverter sysEntityEntityToSysEntityModelConverter = new SysEntityEntityToSysEntityModelConverter(Boolean.FALSE);
    private static final SysEntityEntityToSysEntityModelConverter sysEntityEntityToSysEntityModelEagerConverter = new SysEntityEntityToSysEntityModelConverter(Boolean.TRUE);

    private static final SysEntityModelToSysEntityEntityConverter sysEntityModelToSysEntityEntityConverter = new SysEntityModelToSysEntityEntityConverter();

    public static List<SysEntityModel> sysEntityEntityToSysEntityModel(List<SysEntity> sysEntityEntityList){
        List<SysEntityModel> sysEntityModelList = BeanCopyUtil.listCopy(sysEntityEntityList,SysEntityModel.class,sysEntityToSysEntityModelBeanCopier,sysEntityEntityToSysEntityModelConverter);
        return sysEntityModelList;
    }

    public static SysEntityModel sysEntityEntityToSysEntityModel(SysEntity sysEntity){
        SysEntityModel sysEntityModel = new SysEntityModel();
        sysEntityToSysEntityModelBeanCopier.copy(sysEntity, sysEntityModel, sysEntityEntityToSysEntityModelConverter);
        return sysEntityModel;
    }

    public static SysEntityModel sysEntityEntityToSysEntityModelEager(SysEntity sysEntity){
        SysEntityModel sysEntityModel = new SysEntityModel();
        sysEntityToSysEntityModelBeanCopier.copy(sysEntity, sysEntityModel, sysEntityEntityToSysEntityModelEagerConverter);
        return sysEntityModel;
    }

    public static List<SysEntityModel> sysEntityEntityToSysEntityModelEager(List<SysEntity> sysEntityEntityList){
        List<SysEntityModel> sysEntityModelList = BeanCopyUtil.listCopy(sysEntityEntityList,SysEntityModel.class,sysEntityToSysEntityModelBeanCopier,sysEntityEntityToSysEntityModelEagerConverter);
        return sysEntityModelList;
    }

    public static List<SysEntity> sysEntityModelToSysEntityEntity(List<SysEntityModel> sysEntityModelList){
        List<SysEntity> sysEntityEntityList = BeanCopyUtil.listCopy(sysEntityModelList,SysEntity.class,sysEntityToSysEntityModelBeanCopier,sysEntityModelToSysEntityEntityConverter);
        return sysEntityEntityList;
    }

    public static SysEntity sysEntityModelToSysEntityEntity(SysEntityModel sysEntityModel){
        SysEntity sysEntityEntity = new SysEntity();
        sysEntityModelToSysEntityBeanCopier.copy(sysEntityModel,sysEntityEntity,sysEntityModelToSysEntityEntityConverter);
        return sysEntityEntity;
    }
}