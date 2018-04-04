package com.laputa.foundation.web.generate.converter;

import com.laputa.foundation.web.generate.entity.SysHelloMenu;
import com.laputa.foundation.web.generate.model.SysHelloMenuModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * Hi菜单  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-01-10T18:13:30+08:00 .
*/
public class SysHelloMenuBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysHelloMenuModelToSysHelloMenuBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloMenuModel.class, SysHelloMenu.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysHelloMenuToSysHelloMenuModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysHelloMenu.class, SysHelloMenuModel.class, true);


    private static final SysHelloMenuEntityToSysHelloMenuModelConverter sysHelloMenuEntityToSysHelloMenuModelConverter = new SysHelloMenuEntityToSysHelloMenuModelConverter(Boolean.FALSE);
    private static final SysHelloMenuEntityToSysHelloMenuModelConverter sysHelloMenuEntityToSysHelloMenuModelEagerConverter = new SysHelloMenuEntityToSysHelloMenuModelConverter(Boolean.TRUE);

    private static final SysHelloMenuModelToSysHelloMenuEntityConverter sysHelloMenuModelToSysHelloMenuEntityConverter = new SysHelloMenuModelToSysHelloMenuEntityConverter();

    public static List<SysHelloMenuModel> sysHelloMenuEntityToSysHelloMenuModel(List<SysHelloMenu> sysHelloMenuEntityList){
        List<SysHelloMenuModel> sysHelloMenuModelList = BeanCopyUtil.listCopy(sysHelloMenuEntityList,SysHelloMenuModel.class,sysHelloMenuToSysHelloMenuModelBeanCopier,sysHelloMenuEntityToSysHelloMenuModelConverter);
        return sysHelloMenuModelList;
    }

    public static SysHelloMenuModel sysHelloMenuEntityToSysHelloMenuModel(SysHelloMenu sysHelloMenu){
        SysHelloMenuModel sysHelloMenuModel = new SysHelloMenuModel();
        sysHelloMenuToSysHelloMenuModelBeanCopier.copy(sysHelloMenu, sysHelloMenuModel, sysHelloMenuEntityToSysHelloMenuModelConverter);
        return sysHelloMenuModel;
    }

    public static SysHelloMenuModel sysHelloMenuEntityToSysHelloMenuModelEager(SysHelloMenu sysHelloMenu){
        SysHelloMenuModel sysHelloMenuModel = new SysHelloMenuModel();
        sysHelloMenuToSysHelloMenuModelBeanCopier.copy(sysHelloMenu, sysHelloMenuModel, sysHelloMenuEntityToSysHelloMenuModelEagerConverter);
        return sysHelloMenuModel;
    }

    public static List<SysHelloMenuModel> sysHelloMenuEntityToSysHelloMenuModelEager(List<SysHelloMenu> sysHelloMenuEntityList){
        List<SysHelloMenuModel> sysHelloMenuModelList = BeanCopyUtil.listCopy(sysHelloMenuEntityList,SysHelloMenuModel.class,sysHelloMenuToSysHelloMenuModelBeanCopier,sysHelloMenuEntityToSysHelloMenuModelEagerConverter);
        return sysHelloMenuModelList;
    }

    public static List<SysHelloMenu> sysHelloMenuModelToSysHelloMenuEntity(List<SysHelloMenuModel> sysHelloMenuModelList){
        List<SysHelloMenu> sysHelloMenuEntityList = BeanCopyUtil.listCopy(sysHelloMenuModelList,SysHelloMenu.class,sysHelloMenuToSysHelloMenuModelBeanCopier,sysHelloMenuModelToSysHelloMenuEntityConverter);
        return sysHelloMenuEntityList;
    }

    public static SysHelloMenu sysHelloMenuModelToSysHelloMenuEntity(SysHelloMenuModel sysHelloMenuModel){
        SysHelloMenu sysHelloMenuEntity = new SysHelloMenu();
        sysHelloMenuModelToSysHelloMenuBeanCopier.copy(sysHelloMenuModel,sysHelloMenuEntity,sysHelloMenuModelToSysHelloMenuEntityConverter);
        return sysHelloMenuEntity;
    }
}