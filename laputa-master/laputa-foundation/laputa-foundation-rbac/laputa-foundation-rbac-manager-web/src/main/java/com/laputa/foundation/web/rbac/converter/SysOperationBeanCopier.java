package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.model.SysOperationModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 功能操作  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:42+08:00 .
*/
public class SysOperationBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysOperationModelToSysOperationBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysOperationModel.class, SysOperation.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysOperationToSysOperationModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysOperation.class, SysOperationModel.class, true);


    private static final SysOperationEntityToSysOperationModelConverter sysOperationEntityToSysOperationModelConverter = new SysOperationEntityToSysOperationModelConverter(Boolean.FALSE);
    private static final SysOperationEntityToSysOperationModelConverter sysOperationEntityToSysOperationModelEagerConverter = new SysOperationEntityToSysOperationModelConverter(Boolean.TRUE);

    private static final SysOperationModelToSysOperationEntityConverter sysOperationModelToSysOperationEntityConverter = new SysOperationModelToSysOperationEntityConverter();

    public static List<SysOperationModel> sysOperationEntityToSysOperationModel(List<SysOperation> sysOperationEntityList){
        List<SysOperationModel> sysOperationModelList = BeanCopyUtil.listCopy(sysOperationEntityList,SysOperationModel.class,sysOperationToSysOperationModelBeanCopier,sysOperationEntityToSysOperationModelConverter);
        return sysOperationModelList;
    }

    public static SysOperationModel sysOperationEntityToSysOperationModel(SysOperation sysOperation){
        SysOperationModel sysOperationModel = new SysOperationModel();
        sysOperationToSysOperationModelBeanCopier.copy(sysOperation, sysOperationModel, sysOperationEntityToSysOperationModelConverter);
        return sysOperationModel;
    }

    public static SysOperationModel sysOperationEntityToSysOperationModelEager(SysOperation sysOperation){
        SysOperationModel sysOperationModel = new SysOperationModel();
        sysOperationToSysOperationModelBeanCopier.copy(sysOperation, sysOperationModel, sysOperationEntityToSysOperationModelEagerConverter);
        return sysOperationModel;
    }

    public static List<SysOperationModel> sysOperationEntityToSysOperationModelEager(List<SysOperation> sysOperationEntityList){
        List<SysOperationModel> sysOperationModelList = BeanCopyUtil.listCopy(sysOperationEntityList,SysOperationModel.class,sysOperationToSysOperationModelBeanCopier,sysOperationEntityToSysOperationModelEagerConverter);
        return sysOperationModelList;
    }

    public static List<SysOperation> sysOperationModelToSysOperationEntity(List<SysOperationModel> sysOperationModelList){
        List<SysOperation> sysOperationEntityList = BeanCopyUtil.listCopy(sysOperationModelList,SysOperation.class,sysOperationToSysOperationModelBeanCopier,sysOperationModelToSysOperationEntityConverter);
        return sysOperationEntityList;
    }

    public static SysOperation sysOperationModelToSysOperationEntity(SysOperationModel sysOperationModel){
        SysOperation sysOperationEntity = new SysOperation();
        sysOperationModelToSysOperationBeanCopier.copy(sysOperationModel,sysOperationEntity,sysOperationModelToSysOperationEntityConverter);
        return sysOperationEntity;
    }
}