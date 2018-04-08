package com.laputa.fates.web.converter;

import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.fates.web.model.LaputaConfigHistoryModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Laputa项目  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:11+08:00 .
*/
public class LaputaConfigHistoryBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier laputaConfigHistoryModelToLaputaConfigHistoryBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaConfigHistoryModel.class, LaputaConfigHistory.class, true);

    private static final org.springframework.cglib.beans.BeanCopier laputaConfigHistoryToLaputaConfigHistoryModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(LaputaConfigHistory.class, LaputaConfigHistoryModel.class, true);


    private static final LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter laputaConfigHistoryEntityToLaputaConfigHistoryModelConverter = new LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter(Boolean.FALSE);
    private static final LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter laputaConfigHistoryEntityToLaputaConfigHistoryModelEagerConverter = new LaputaConfigHistoryEntityToLaputaConfigHistoryModelConverter(Boolean.TRUE);

    private static final LaputaConfigHistoryModelToLaputaConfigHistoryEntityConverter laputaConfigHistoryModelToLaputaConfigHistoryEntityConverter = new LaputaConfigHistoryModelToLaputaConfigHistoryEntityConverter();

    public static List<LaputaConfigHistoryModel> laputaConfigHistoryEntityToLaputaConfigHistoryModel(List<LaputaConfigHistory> laputaConfigHistoryEntityList){
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = BeanCopyUtil.listCopy(laputaConfigHistoryEntityList,LaputaConfigHistoryModel.class,laputaConfigHistoryToLaputaConfigHistoryModelBeanCopier,laputaConfigHistoryEntityToLaputaConfigHistoryModelConverter);
        return laputaConfigHistoryModelList;
    }

    public static LaputaConfigHistoryModel laputaConfigHistoryEntityToLaputaConfigHistoryModel(LaputaConfigHistory laputaConfigHistory){
        LaputaConfigHistoryModel laputaConfigHistoryModel = new LaputaConfigHistoryModel();
        laputaConfigHistoryToLaputaConfigHistoryModelBeanCopier.copy(laputaConfigHistory, laputaConfigHistoryModel, laputaConfigHistoryEntityToLaputaConfigHistoryModelConverter);
        return laputaConfigHistoryModel;
    }

    public static LaputaConfigHistoryModel laputaConfigHistoryEntityToLaputaConfigHistoryModelEager(LaputaConfigHistory laputaConfigHistory){
        LaputaConfigHistoryModel laputaConfigHistoryModel = new LaputaConfigHistoryModel();
        laputaConfigHistoryToLaputaConfigHistoryModelBeanCopier.copy(laputaConfigHistory, laputaConfigHistoryModel, laputaConfigHistoryEntityToLaputaConfigHistoryModelEagerConverter);
        return laputaConfigHistoryModel;
    }

    public static List<LaputaConfigHistoryModel> laputaConfigHistoryEntityToLaputaConfigHistoryModelEager(List<LaputaConfigHistory> laputaConfigHistoryEntityList){
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = BeanCopyUtil.listCopy(laputaConfigHistoryEntityList,LaputaConfigHistoryModel.class,laputaConfigHistoryToLaputaConfigHistoryModelBeanCopier,laputaConfigHistoryEntityToLaputaConfigHistoryModelEagerConverter);
        return laputaConfigHistoryModelList;
    }

    public static List<LaputaConfigHistory> laputaConfigHistoryModelToLaputaConfigHistoryEntity(List<LaputaConfigHistoryModel> laputaConfigHistoryModelList){
        List<LaputaConfigHistory> laputaConfigHistoryEntityList = BeanCopyUtil.listCopy(laputaConfigHistoryModelList,LaputaConfigHistory.class,laputaConfigHistoryModelToLaputaConfigHistoryBeanCopier,laputaConfigHistoryModelToLaputaConfigHistoryEntityConverter);
        return laputaConfigHistoryEntityList;
    }

    public static LaputaConfigHistory laputaConfigHistoryModelToLaputaConfigHistoryEntity(LaputaConfigHistoryModel laputaConfigHistoryModel){
        LaputaConfigHistory laputaConfigHistoryEntity = new LaputaConfigHistory();
        laputaConfigHistoryModelToLaputaConfigHistoryBeanCopier.copy(laputaConfigHistoryModel,laputaConfigHistoryEntity,laputaConfigHistoryModelToLaputaConfigHistoryEntityConverter);
        return laputaConfigHistoryEntity;
    }
}