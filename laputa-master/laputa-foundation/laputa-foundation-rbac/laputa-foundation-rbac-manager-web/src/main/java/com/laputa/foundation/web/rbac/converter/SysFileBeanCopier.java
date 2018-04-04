package com.laputa.foundation.web.rbac.converter;

import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.model.SysFileModel;

import com.laputa.foundation.spring.beancopy.BeanCopyUtil;

import java.util.List;

import java.lang.Boolean;

/**
 * <p>
 * 系统文件  BeanCopier<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2017-02-03T16:53:41+08:00 .
*/
public class SysFileBeanCopier {

    private static final org.springframework.cglib.beans.BeanCopier sysFileModelToSysFileBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysFileModel.class, SysFile.class, true);

    private static final org.springframework.cglib.beans.BeanCopier sysFileToSysFileModelBeanCopier = org.springframework.cglib.beans.BeanCopier.create(SysFile.class, SysFileModel.class, true);


    private static final SysFileEntityToSysFileModelConverter sysFileEntityToSysFileModelConverter = new SysFileEntityToSysFileModelConverter(Boolean.FALSE);
    private static final SysFileEntityToSysFileModelConverter sysFileEntityToSysFileModelEagerConverter = new SysFileEntityToSysFileModelConverter(Boolean.TRUE);

    private static final SysFileModelToSysFileEntityConverter sysFileModelToSysFileEntityConverter = new SysFileModelToSysFileEntityConverter();

    public static List<SysFileModel> sysFileEntityToSysFileModel(List<SysFile> sysFileEntityList){
        List<SysFileModel> sysFileModelList = BeanCopyUtil.listCopy(sysFileEntityList,SysFileModel.class,sysFileToSysFileModelBeanCopier,sysFileEntityToSysFileModelConverter);
        return sysFileModelList;
    }

    public static SysFileModel sysFileEntityToSysFileModel(SysFile sysFile){
        SysFileModel sysFileModel = new SysFileModel();
        sysFileToSysFileModelBeanCopier.copy(sysFile, sysFileModel, sysFileEntityToSysFileModelConverter);
        return sysFileModel;
    }

    public static SysFileModel sysFileEntityToSysFileModelEager(SysFile sysFile){
        SysFileModel sysFileModel = new SysFileModel();
        sysFileToSysFileModelBeanCopier.copy(sysFile, sysFileModel, sysFileEntityToSysFileModelEagerConverter);
        return sysFileModel;
    }

    public static List<SysFileModel> sysFileEntityToSysFileModelEager(List<SysFile> sysFileEntityList){
        List<SysFileModel> sysFileModelList = BeanCopyUtil.listCopy(sysFileEntityList,SysFileModel.class,sysFileToSysFileModelBeanCopier,sysFileEntityToSysFileModelEagerConverter);
        return sysFileModelList;
    }

    public static List<SysFile> sysFileModelToSysFileEntity(List<SysFileModel> sysFileModelList){
        List<SysFile> sysFileEntityList = BeanCopyUtil.listCopy(sysFileModelList,SysFile.class,sysFileToSysFileModelBeanCopier,sysFileModelToSysFileEntityConverter);
        return sysFileEntityList;
    }

    public static SysFile sysFileModelToSysFileEntity(SysFileModel sysFileModel){
        SysFile sysFileEntity = new SysFile();
        sysFileModelToSysFileBeanCopier.copy(sysFileModel,sysFileEntity,sysFileModelToSysFileEntityConverter);
        return sysFileEntity;
    }
}