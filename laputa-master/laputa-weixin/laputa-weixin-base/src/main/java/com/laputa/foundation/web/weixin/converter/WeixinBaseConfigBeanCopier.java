package com.laputa.foundation.web.weixin.converter;

import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.model.WeixinBaseConfigModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 微信公众号基础配置  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-13T16:56:42+08:00 .
*/
public class WeixinBaseConfigBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier weixinBaseConfigModelToWeixinBaseConfigBeanCopier = org.springframework.cglib.beans.BeanCopier.create(WeixinBaseConfigModel.class, WeixinBaseConfig.class, true);

    private static final org.springframework.cglib.beans.BeanCopier weixinBaseConfigToWeixinBaseConfigModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(WeixinBaseConfig.class, WeixinBaseConfigModel.class, true);


    private static final WeixinBaseConfigEntityToWeixinBaseConfigModelConverter weixinBaseConfigEntityToWeixinBaseConfigModelConverter = new WeixinBaseConfigEntityToWeixinBaseConfigModelConverter(Boolean.FALSE);
    private static final WeixinBaseConfigEntityToWeixinBaseConfigModelConverter weixinBaseConfigEntityToWeixinBaseConfigModelEagerConverter = new WeixinBaseConfigEntityToWeixinBaseConfigModelConverter(Boolean.TRUE);

    private static final WeixinBaseConfigModelToWeixinBaseConfigEntityConverter weixinBaseConfigModelToWeixinBaseConfigEntityConverter = new WeixinBaseConfigModelToWeixinBaseConfigEntityConverter();

    public static List<WeixinBaseConfigModel> weixinBaseConfigEntityToWeixinBaseConfigModel(List<WeixinBaseConfig> weixinBaseConfigEntityList){
        List<WeixinBaseConfigModel> weixinBaseConfigModelList = BeanCopyUtil.listCopy(weixinBaseConfigEntityList,WeixinBaseConfigModel.class,weixinBaseConfigToWeixinBaseConfigModelBeanCopier,weixinBaseConfigEntityToWeixinBaseConfigModelConverter);
        return weixinBaseConfigModelList;
    }

    public static WeixinBaseConfigModel weixinBaseConfigEntityToWeixinBaseConfigModel(WeixinBaseConfig weixinBaseConfig){
        WeixinBaseConfigModel weixinBaseConfigModel = new WeixinBaseConfigModel();
        weixinBaseConfigToWeixinBaseConfigModelBeanCopier.copy(weixinBaseConfig, weixinBaseConfigModel, weixinBaseConfigEntityToWeixinBaseConfigModelConverter);
        return weixinBaseConfigModel;
    }

    public static WeixinBaseConfigModel weixinBaseConfigEntityToWeixinBaseConfigModelEager(WeixinBaseConfig weixinBaseConfig){
        WeixinBaseConfigModel weixinBaseConfigModel = new WeixinBaseConfigModel();
        weixinBaseConfigToWeixinBaseConfigModelBeanCopier.copy(weixinBaseConfig, weixinBaseConfigModel, weixinBaseConfigEntityToWeixinBaseConfigModelEagerConverter);
        return weixinBaseConfigModel;
    }

    public static List<WeixinBaseConfigModel> weixinBaseConfigEntityToWeixinBaseConfigModelEager(List<WeixinBaseConfig> weixinBaseConfigEntityList){
        List<WeixinBaseConfigModel> weixinBaseConfigModelList = BeanCopyUtil.listCopy(weixinBaseConfigEntityList,WeixinBaseConfigModel.class,weixinBaseConfigToWeixinBaseConfigModelBeanCopier,weixinBaseConfigEntityToWeixinBaseConfigModelEagerConverter);
        return weixinBaseConfigModelList;
    }

    public static List<WeixinBaseConfig> weixinBaseConfigModelToWeixinBaseConfigEntity(List<WeixinBaseConfigModel> weixinBaseConfigModelList){
        List<WeixinBaseConfig> weixinBaseConfigEntityList = BeanCopyUtil.listCopy(weixinBaseConfigModelList,WeixinBaseConfig.class,weixinBaseConfigToWeixinBaseConfigModelBeanCopier,weixinBaseConfigModelToWeixinBaseConfigEntityConverter);
        return weixinBaseConfigEntityList;
    }

    public static WeixinBaseConfig weixinBaseConfigModelToWeixinBaseConfigEntity(WeixinBaseConfigModel weixinBaseConfigModel){
        WeixinBaseConfig weixinBaseConfigEntity = new WeixinBaseConfig();
        weixinBaseConfigModelToWeixinBaseConfigBeanCopier.copy(weixinBaseConfigModel,weixinBaseConfigEntity,weixinBaseConfigModelToWeixinBaseConfigEntityConverter);
        return weixinBaseConfigEntity;
    }
}