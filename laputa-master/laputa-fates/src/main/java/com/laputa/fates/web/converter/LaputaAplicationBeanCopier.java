package com.laputa.fates.web.converter;

import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.model.LaputaAplicationModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:08+08:00 .
*/
public class LaputaAplicationBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier laputaAplicationModelToLaputaAplicationBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaAplicationModel.class, LaputaAplication.class, true);

    private static final org.springframework.cglib.beans.BeanCopier laputaAplicationToLaputaAplicationModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaAplication.class, LaputaAplicationModel.class, true);


    private static final LaputaAplicationEntityToLaputaAplicationModelConverter laputaAplicationEntityToLaputaAplicationModelConverter = new LaputaAplicationEntityToLaputaAplicationModelConverter(Boolean.FALSE);
    private static final LaputaAplicationEntityToLaputaAplicationModelConverter laputaAplicationEntityToLaputaAplicationModelEagerConverter = new LaputaAplicationEntityToLaputaAplicationModelConverter(Boolean.TRUE);

    private static final LaputaAplicationModelToLaputaAplicationEntityConverter laputaAplicationModelToLaputaAplicationEntityConverter = new LaputaAplicationModelToLaputaAplicationEntityConverter();

    public static List<LaputaAplicationModel> laputaAplicationEntityToLaputaAplicationModel(List<LaputaAplication> laputaAplicationEntityList){
        List<LaputaAplicationModel> laputaAplicationModelList = BeanCopyUtil.listCopy(laputaAplicationEntityList,LaputaAplicationModel.class,laputaAplicationToLaputaAplicationModelBeanCopier,laputaAplicationEntityToLaputaAplicationModelConverter);
        return laputaAplicationModelList;
    }

    public static LaputaAplicationModel laputaAplicationEntityToLaputaAplicationModel(LaputaAplication laputaAplication){
        LaputaAplicationModel laputaAplicationModel = new LaputaAplicationModel();
        laputaAplicationToLaputaAplicationModelBeanCopier.copy(laputaAplication, laputaAplicationModel, laputaAplicationEntityToLaputaAplicationModelConverter);
        return laputaAplicationModel;
    }

    public static LaputaAplicationModel laputaAplicationEntityToLaputaAplicationModelEager(LaputaAplication laputaAplication){
        LaputaAplicationModel laputaAplicationModel = new LaputaAplicationModel();
        laputaAplicationToLaputaAplicationModelBeanCopier.copy(laputaAplication, laputaAplicationModel, laputaAplicationEntityToLaputaAplicationModelEagerConverter);
        return laputaAplicationModel;
    }

    public static List<LaputaAplicationModel> laputaAplicationEntityToLaputaAplicationModelEager(List<LaputaAplication> laputaAplicationEntityList){
        List<LaputaAplicationModel> laputaAplicationModelList = BeanCopyUtil.listCopy(laputaAplicationEntityList,LaputaAplicationModel.class,laputaAplicationToLaputaAplicationModelBeanCopier,laputaAplicationEntityToLaputaAplicationModelEagerConverter);
        return laputaAplicationModelList;
    }

    public static List<LaputaAplication> laputaAplicationModelToLaputaAplicationEntity(List<LaputaAplicationModel> laputaAplicationModelList){
        List<LaputaAplication> laputaAplicationEntityList = BeanCopyUtil.listCopy(laputaAplicationModelList,LaputaAplication.class,laputaAplicationModelToLaputaAplicationBeanCopier,laputaAplicationModelToLaputaAplicationEntityConverter);
        return laputaAplicationEntityList;
    }

    public static LaputaAplication laputaAplicationModelToLaputaAplicationEntity(LaputaAplicationModel laputaAplicationModel){
        LaputaAplication laputaAplicationEntity = new LaputaAplication();
        laputaAplicationModelToLaputaAplicationBeanCopier.copy(laputaAplicationModel,laputaAplicationEntity,laputaAplicationModelToLaputaAplicationEntityConverter);
        return laputaAplicationEntity;
    }
}