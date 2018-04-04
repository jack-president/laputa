package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.model.SysMenuModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 菜单  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
*/
public class SysMenuBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysMenuModelToSysMenuBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysMenuModel.class, SysMenu.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysMenuToSysMenuModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysMenu.class, SysMenuModel.class, true);


    private static final SysMenuEntityToSysMenuModelConverter sysMenuEntityToSysMenuModelConverter = new SysMenuEntityToSysMenuModelConverter(Boolean.FALSE);
    private static final SysMenuEntityToSysMenuModelConverter sysMenuEntityToSysMenuModelEagerConverter = new SysMenuEntityToSysMenuModelConverter(Boolean.TRUE);

    private static final SysMenuModelToSysMenuEntityConverter sysMenuModelToSysMenuEntityConverter = new SysMenuModelToSysMenuEntityConverter();

    public static List<SysMenuModel> sysMenuEntityToSysMenuModel(List<SysMenu> sysMenuEntityList){
        List<SysMenuModel> sysMenuModelList = BeanCopyUtil.listCopy(sysMenuEntityList,SysMenuModel.class,sysMenuToSysMenuModelBeanCopier,sysMenuEntityToSysMenuModelConverter);
        return sysMenuModelList;
    }

    public static SysMenuModel sysMenuEntityToSysMenuModel(SysMenu sysMenu){
        SysMenuModel sysMenuModel = new SysMenuModel();
        sysMenuToSysMenuModelBeanCopier.copy(sysMenu, sysMenuModel, sysMenuEntityToSysMenuModelConverter);
        return sysMenuModel;
    }

    public static SysMenuModel sysMenuEntityToSysMenuModelEager(SysMenu sysMenu){
        SysMenuModel sysMenuModel = new SysMenuModel();
        sysMenuToSysMenuModelBeanCopier.copy(sysMenu, sysMenuModel, sysMenuEntityToSysMenuModelEagerConverter);
        return sysMenuModel;
    }

    public static List<SysMenuModel> sysMenuEntityToSysMenuModelEager(List<SysMenu> sysMenuEntityList){
        List<SysMenuModel> sysMenuModelList = BeanCopyUtil.listCopy(sysMenuEntityList,SysMenuModel.class,sysMenuToSysMenuModelBeanCopier,sysMenuEntityToSysMenuModelEagerConverter);
        return sysMenuModelList;
    }

    public static List<SysMenu> sysMenuModelToSysMenuEntity(List<SysMenuModel> sysMenuModelList){
        List<SysMenu> sysMenuEntityList = BeanCopyUtil.listCopy(sysMenuModelList,SysMenu.class,sysMenuToSysMenuModelBeanCopier,sysMenuModelToSysMenuEntityConverter);
        return sysMenuEntityList;
    }

    public static SysMenu sysMenuModelToSysMenuEntity(SysMenuModel sysMenuModel){
        SysMenu sysMenuEntity = new SysMenu();
        sysMenuModelToSysMenuBeanCopier.copy(sysMenuModel,sysMenuEntity,sysMenuModelToSysMenuEntityConverter);
        return sysMenuEntity;
    }
}