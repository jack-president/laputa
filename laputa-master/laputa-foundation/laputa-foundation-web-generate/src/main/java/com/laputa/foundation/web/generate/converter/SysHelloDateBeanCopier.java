package com.laputa.foundation.web.generate.converter;

import com.laputa.foundation.web.generate.entity.SysHelloDate;
import com.laputa.foundation.web.generate.model.SysHelloDateModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi时间  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2016-12-07T16:50:06+08:00 .
*/
public class SysHelloDateBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysHelloDateModelToSysHelloDateBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloDateModel.class, SysHelloDate.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysHelloDateToSysHelloDateModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloDate.class, SysHelloDateModel.class, true);


    private static final SysHelloDateEntityToSysHelloDateModelConverter sysHelloDateEntityToSysHelloDateModelConverter = new SysHelloDateEntityToSysHelloDateModelConverter(Boolean.FALSE);
    private static final SysHelloDateEntityToSysHelloDateModelConverter sysHelloDateEntityToSysHelloDateModelEagerConverter = new SysHelloDateEntityToSysHelloDateModelConverter(Boolean.TRUE);

    private static final SysHelloDateModelToSysHelloDateEntityConverter sysHelloDateModelToSysHelloDateEntityConverter = new SysHelloDateModelToSysHelloDateEntityConverter();

    public static List<SysHelloDateModel> sysHelloDateEntityToSysHelloDateModel(List<SysHelloDate> sysHelloDateEntityList){
        List<SysHelloDateModel> sysHelloDateModelList = BeanCopyUtil.listCopy(sysHelloDateEntityList,SysHelloDateModel.class,sysHelloDateToSysHelloDateModelBeanCopier,sysHelloDateEntityToSysHelloDateModelConverter);
        return sysHelloDateModelList;
    }

    public static SysHelloDateModel sysHelloDateEntityToSysHelloDateModel(SysHelloDate sysHelloDate){
        SysHelloDateModel sysHelloDateModel = new SysHelloDateModel();
        sysHelloDateToSysHelloDateModelBeanCopier.copy(sysHelloDate, sysHelloDateModel, sysHelloDateEntityToSysHelloDateModelConverter);
        return sysHelloDateModel;
    }

    public static SysHelloDateModel sysHelloDateEntityToSysHelloDateModelEager(SysHelloDate sysHelloDate){
        SysHelloDateModel sysHelloDateModel = new SysHelloDateModel();
        sysHelloDateToSysHelloDateModelBeanCopier.copy(sysHelloDate, sysHelloDateModel, sysHelloDateEntityToSysHelloDateModelEagerConverter);
        return sysHelloDateModel;
    }

    public static List<SysHelloDateModel> sysHelloDateEntityToSysHelloDateModelEager(List<SysHelloDate> sysHelloDateEntityList){
        List<SysHelloDateModel> sysHelloDateModelList = BeanCopyUtil.listCopy(sysHelloDateEntityList,SysHelloDateModel.class,sysHelloDateToSysHelloDateModelBeanCopier,sysHelloDateEntityToSysHelloDateModelEagerConverter);
        return sysHelloDateModelList;
    }

    public static List<SysHelloDate> sysHelloDateModelToSysHelloDateEntity(List<SysHelloDateModel> sysHelloDateModelList){
        List<SysHelloDate> sysHelloDateEntityList = BeanCopyUtil.listCopy(sysHelloDateModelList,SysHelloDate.class,sysHelloDateToSysHelloDateModelBeanCopier,sysHelloDateModelToSysHelloDateEntityConverter);
        return sysHelloDateEntityList;
    }

    public static SysHelloDate sysHelloDateModelToSysHelloDateEntity(SysHelloDateModel sysHelloDateModel){
        SysHelloDate sysHelloDateEntity = new SysHelloDate();
        sysHelloDateModelToSysHelloDateBeanCopier.copy(sysHelloDateModel,sysHelloDateEntity,sysHelloDateModelToSysHelloDateEntityConverter);
        return sysHelloDateEntity;
    }
}