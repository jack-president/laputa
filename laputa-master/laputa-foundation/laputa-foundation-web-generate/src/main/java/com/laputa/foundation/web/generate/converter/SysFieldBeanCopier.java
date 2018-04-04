package com.laputa.foundation.web.generate.converter;

import com.laputa.foundation.web.generate.entity.SysField;
import com.laputa.foundation.web.generate.model.SysFieldModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 实体表  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-11-30T17:23:51+08:00 .
*/
public class SysFieldBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysFieldModelToSysFieldBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysFieldModel.class, SysField.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysFieldToSysFieldModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysField.class, SysFieldModel.class, true);


    private static final SysFieldEntityToSysFieldModelConverter sysFieldEntityToSysFieldModelConverter = new SysFieldEntityToSysFieldModelConverter(Boolean.FALSE);
    private static final SysFieldEntityToSysFieldModelConverter sysFieldEntityToSysFieldModelEagerConverter = new SysFieldEntityToSysFieldModelConverter(Boolean.TRUE);

    private static final SysFieldModelToSysFieldEntityConverter sysFieldModelToSysFieldEntityConverter = new SysFieldModelToSysFieldEntityConverter();

    public static List<SysFieldModel> sysFieldEntityToSysFieldModel(List<SysField> sysFieldEntityList){
        List<SysFieldModel> sysFieldModelList = BeanCopyUtil.listCopy(sysFieldEntityList,SysFieldModel.class,sysFieldToSysFieldModelBeanCopier,sysFieldEntityToSysFieldModelConverter);
        return sysFieldModelList;
    }

    public static SysFieldModel sysFieldEntityToSysFieldModel(SysField sysField){
        SysFieldModel sysFieldModel = new SysFieldModel();
        sysFieldToSysFieldModelBeanCopier.copy(sysField, sysFieldModel, sysFieldEntityToSysFieldModelConverter);
        return sysFieldModel;
    }

    public static SysFieldModel sysFieldEntityToSysFieldModelEager(SysField sysField){
        SysFieldModel sysFieldModel = new SysFieldModel();
        sysFieldToSysFieldModelBeanCopier.copy(sysField, sysFieldModel, sysFieldEntityToSysFieldModelEagerConverter);
        return sysFieldModel;
    }

    public static List<SysFieldModel> sysFieldEntityToSysFieldModelEager(List<SysField> sysFieldEntityList){
        List<SysFieldModel> sysFieldModelList = BeanCopyUtil.listCopy(sysFieldEntityList,SysFieldModel.class,sysFieldToSysFieldModelBeanCopier,sysFieldEntityToSysFieldModelEagerConverter);
        return sysFieldModelList;
    }

    public static List<SysField> sysFieldModelToSysFieldEntity(List<SysFieldModel> sysFieldModelList){
        List<SysField> sysFieldEntityList = BeanCopyUtil.listCopy(sysFieldModelList,SysField.class,sysFieldToSysFieldModelBeanCopier,sysFieldModelToSysFieldEntityConverter);
        return sysFieldEntityList;
    }

    public static SysField sysFieldModelToSysFieldEntity(SysFieldModel sysFieldModel){
        SysField sysFieldEntity = new SysField();
        sysFieldModelToSysFieldBeanCopier.copy(sysFieldModel,sysFieldEntity,sysFieldModelToSysFieldEntityConverter);
        return sysFieldEntity;
    }
}