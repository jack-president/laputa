package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.model.SysElementModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 系统元素  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:40+08:00 .
*/
public class SysElementBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysElementModelToSysElementBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysElementModel.class, SysElement.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysElementToSysElementModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysElement.class, SysElementModel.class, true);


    private static final SysElementEntityToSysElementModelConverter sysElementEntityToSysElementModelConverter = new SysElementEntityToSysElementModelConverter(Boolean.FALSE);
    private static final SysElementEntityToSysElementModelConverter sysElementEntityToSysElementModelEagerConverter = new SysElementEntityToSysElementModelConverter(Boolean.TRUE);

    private static final SysElementModelToSysElementEntityConverter sysElementModelToSysElementEntityConverter = new SysElementModelToSysElementEntityConverter();

    public static List<SysElementModel> sysElementEntityToSysElementModel(List<SysElement> sysElementEntityList){
        List<SysElementModel> sysElementModelList = BeanCopyUtil.listCopy(sysElementEntityList,SysElementModel.class,sysElementToSysElementModelBeanCopier,sysElementEntityToSysElementModelConverter);
        return sysElementModelList;
    }

    public static SysElementModel sysElementEntityToSysElementModel(SysElement sysElement){
        SysElementModel sysElementModel = new SysElementModel();
        sysElementToSysElementModelBeanCopier.copy(sysElement, sysElementModel, sysElementEntityToSysElementModelConverter);
        return sysElementModel;
    }

    public static SysElementModel sysElementEntityToSysElementModelEager(SysElement sysElement){
        SysElementModel sysElementModel = new SysElementModel();
        sysElementToSysElementModelBeanCopier.copy(sysElement, sysElementModel, sysElementEntityToSysElementModelEagerConverter);
        return sysElementModel;
    }

    public static List<SysElementModel> sysElementEntityToSysElementModelEager(List<SysElement> sysElementEntityList){
        List<SysElementModel> sysElementModelList = BeanCopyUtil.listCopy(sysElementEntityList,SysElementModel.class,sysElementToSysElementModelBeanCopier,sysElementEntityToSysElementModelEagerConverter);
        return sysElementModelList;
    }

    public static List<SysElement> sysElementModelToSysElementEntity(List<SysElementModel> sysElementModelList){
        List<SysElement> sysElementEntityList = BeanCopyUtil.listCopy(sysElementModelList,SysElement.class,sysElementToSysElementModelBeanCopier,sysElementModelToSysElementEntityConverter);
        return sysElementEntityList;
    }

    public static SysElement sysElementModelToSysElementEntity(SysElementModel sysElementModel){
        SysElement sysElementEntity = new SysElement();
        sysElementModelToSysElementBeanCopier.copy(sysElementModel,sysElementEntity,sysElementModelToSysElementEntityConverter);
        return sysElementEntity;
    }
}