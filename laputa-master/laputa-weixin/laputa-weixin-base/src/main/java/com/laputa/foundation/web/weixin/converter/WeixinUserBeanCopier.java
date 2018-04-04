package com.laputa.foundation.web.weixin.converter;

import com.laputa.foundation.web.weixin.entity.WeixinUser;
import com.laputa.foundation.web.weixin.model.WeixinUserModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 微信用户  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-09-15T17:24:50+08:00 .
*/
public class WeixinUserBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier weixinUserModelToWeixinUserBeanCopier = org.springframework.cglib.beans.BeanCopier.create(WeixinUserModel.class, WeixinUser.class, true);

    private static final org.springframework.cglib.beans.BeanCopier weixinUserToWeixinUserModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(WeixinUser.class, WeixinUserModel.class, true);


    private static final WeixinUserEntityToWeixinUserModelConverter weixinUserEntityToWeixinUserModelConverter = new WeixinUserEntityToWeixinUserModelConverter(Boolean.FALSE);
    private static final WeixinUserEntityToWeixinUserModelConverter weixinUserEntityToWeixinUserModelEagerConverter = new WeixinUserEntityToWeixinUserModelConverter(Boolean.TRUE);

    private static final WeixinUserModelToWeixinUserEntityConverter weixinUserModelToWeixinUserEntityConverter = new WeixinUserModelToWeixinUserEntityConverter();

    public static List<WeixinUserModel> weixinUserEntityToWeixinUserModel(List<WeixinUser> weixinUserEntityList){
        List<WeixinUserModel> weixinUserModelList = BeanCopyUtil.listCopy(weixinUserEntityList,WeixinUserModel.class,weixinUserToWeixinUserModelBeanCopier,weixinUserEntityToWeixinUserModelConverter);
        return weixinUserModelList;
    }

    public static WeixinUserModel weixinUserEntityToWeixinUserModel(WeixinUser weixinUser){
        WeixinUserModel weixinUserModel = new WeixinUserModel();
        weixinUserToWeixinUserModelBeanCopier.copy(weixinUser, weixinUserModel, weixinUserEntityToWeixinUserModelConverter);
        return weixinUserModel;
    }

    public static WeixinUserModel weixinUserEntityToWeixinUserModelEager(WeixinUser weixinUser){
        WeixinUserModel weixinUserModel = new WeixinUserModel();
        weixinUserToWeixinUserModelBeanCopier.copy(weixinUser, weixinUserModel, weixinUserEntityToWeixinUserModelEagerConverter);
        return weixinUserModel;
    }

    public static List<WeixinUserModel> weixinUserEntityToWeixinUserModelEager(List<WeixinUser> weixinUserEntityList){
        List<WeixinUserModel> weixinUserModelList = BeanCopyUtil.listCopy(weixinUserEntityList,WeixinUserModel.class,weixinUserToWeixinUserModelBeanCopier,weixinUserEntityToWeixinUserModelEagerConverter);
        return weixinUserModelList;
    }

    public static List<WeixinUser> weixinUserModelToWeixinUserEntity(List<WeixinUserModel> weixinUserModelList){
        List<WeixinUser> weixinUserEntityList = BeanCopyUtil.listCopy(weixinUserModelList,WeixinUser.class,weixinUserToWeixinUserModelBeanCopier,weixinUserModelToWeixinUserEntityConverter);
        return weixinUserEntityList;
    }

    public static WeixinUser weixinUserModelToWeixinUserEntity(WeixinUserModel weixinUserModel){
        WeixinUser weixinUserEntity = new WeixinUser();
        weixinUserModelToWeixinUserBeanCopier.copy(weixinUserModel,weixinUserEntity,weixinUserModelToWeixinUserEntityConverter);
        return weixinUserEntity;
    }
}