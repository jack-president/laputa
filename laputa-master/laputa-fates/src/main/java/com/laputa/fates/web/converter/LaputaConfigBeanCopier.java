package com.laputa.fates.web.converter;

import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.model.LaputaConfigModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
*/
public class LaputaConfigBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier laputaConfigModelToLaputaConfigBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaConfigModel.class, LaputaConfig.class, true);

    private static final org.springframework.cglib.beans.BeanCopier laputaConfigToLaputaConfigModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaConfig.class, LaputaConfigModel.class, true);


    private static final LaputaConfigEntityToLaputaConfigModelConverter laputaConfigEntityToLaputaConfigModelConverter = new LaputaConfigEntityToLaputaConfigModelConverter(Boolean.FALSE);
    private static final LaputaConfigEntityToLaputaConfigModelConverter laputaConfigEntityToLaputaConfigModelEagerConverter = new LaputaConfigEntityToLaputaConfigModelConverter(Boolean.TRUE);

    private static final LaputaConfigModelToLaputaConfigEntityConverter laputaConfigModelToLaputaConfigEntityConverter = new LaputaConfigModelToLaputaConfigEntityConverter();

    public static List<LaputaConfigModel> laputaConfigEntityToLaputaConfigModel(List<LaputaConfig> laputaConfigEntityList){
        List<LaputaConfigModel> laputaConfigModelList = BeanCopyUtil.listCopy(laputaConfigEntityList,LaputaConfigModel.class,laputaConfigToLaputaConfigModelBeanCopier,laputaConfigEntityToLaputaConfigModelConverter);
        return laputaConfigModelList;
    }

    public static LaputaConfigModel laputaConfigEntityToLaputaConfigModel(LaputaConfig laputaConfig){
        LaputaConfigModel laputaConfigModel = new LaputaConfigModel();
        laputaConfigToLaputaConfigModelBeanCopier.copy(laputaConfig, laputaConfigModel, laputaConfigEntityToLaputaConfigModelConverter);
        return laputaConfigModel;
    }

    public static LaputaConfigModel laputaConfigEntityToLaputaConfigModelEager(LaputaConfig laputaConfig){
        LaputaConfigModel laputaConfigModel = new LaputaConfigModel();
        laputaConfigToLaputaConfigModelBeanCopier.copy(laputaConfig, laputaConfigModel, laputaConfigEntityToLaputaConfigModelEagerConverter);
        return laputaConfigModel;
    }

    public static List<LaputaConfigModel> laputaConfigEntityToLaputaConfigModelEager(List<LaputaConfig> laputaConfigEntityList){
        List<LaputaConfigModel> laputaConfigModelList = BeanCopyUtil.listCopy(laputaConfigEntityList,LaputaConfigModel.class,laputaConfigToLaputaConfigModelBeanCopier,laputaConfigEntityToLaputaConfigModelEagerConverter);
        return laputaConfigModelList;
    }

    public static List<LaputaConfig> laputaConfigModelToLaputaConfigEntity(List<LaputaConfigModel> laputaConfigModelList){
        List<LaputaConfig> laputaConfigEntityList = BeanCopyUtil.listCopy(laputaConfigModelList,LaputaConfig.class,laputaConfigModelToLaputaConfigBeanCopier,laputaConfigModelToLaputaConfigEntityConverter);
        return laputaConfigEntityList;
    }

    public static LaputaConfig laputaConfigModelToLaputaConfigEntity(LaputaConfigModel laputaConfigModel){
        LaputaConfig laputaConfigEntity = new LaputaConfig();
        laputaConfigModelToLaputaConfigBeanCopier.copy(laputaConfigModel,laputaConfigEntity,laputaConfigModelToLaputaConfigEntityConverter);
        return laputaConfigEntity;
    }
}