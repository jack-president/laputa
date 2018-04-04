package com.laputa.foundation.web.generate.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.TemporalType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

import com.laputa.foundation.web.generate.annotae.ColumnType;
import com.laputa.foundation.web.generate.dao.SysEntityDao;
import com.laputa.foundation.web.generate.dao.SysEntityRepository;
import com.laputa.foundation.web.generate.entity.SysEntity;
import com.laputa.foundation.web.generate.entity.SysField;

import cn.org.rapid_framework.generator.Generator;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;

/**
 * 代码文件生成服务
 * Created by JiangDongPing on 2016/11/11.
 */
@Transactional
public class CodeFileService {

    private String outRootDir;

    @Resource
    private SysEntityRepository sysEntityRepository;

    @Transactional
    public void codeGenerate(String clazzName) throws Exception {
        Generator generator = new Generator();
        generator.setOutRootDir(outRootDir);
        //FileUtils.deleteDirectory(FileUtils.getFile(generator.getOutRootDir()));
        //generator.setTemplateRootDir(new ClassPathResource("laputa-config/generatetemplate.zip").getFile());
        generator.setTemplateRootDir(new ClassPathResource("laputa-config/generatetemplate").getFile());

        Map<String, Object> templateModel = new HashMap<>();
        SysEntity sysEntity = sysEntityRepository.findByClazzName(clazzName);

        Map<String, SysEntity> referenceMap = new HashMap<>();
        List<SysEntity> referenceSysEntityList = new ArrayList<>();
        for (SysField sysField : sysEntity.getSysFieldCollection()) {
            if (sysField.getRelationSysEntity() != null) {
                if (referenceMap.get(sysField.getRelationSysEntity().getClazzName()) == null) {
                    referenceSysEntityList.add(sysField.getRelationSysEntity());
                }
                referenceMap.put(sysField.getRelationSysEntity().getClazzName(), sysField.getRelationSysEntity());
            }
        }

        Map<String, SysEntity> nestClazzNameMap = new HashMap<>();
        for (SysField sysField : sysEntity.getSysFieldCollection()) {
            if (sysField.getNestClazzName() != null) {
                if (nestClazzNameMap.get(sysField.getNestClazzName()) == null) {
                    nestClazzNameMap.put(sysField.getNestClazzName(), sysField.getRelationSysEntity());
                }
            }
        }

        templateModel.put("sysEntity", sysEntity);
        templateModel.put(CodeFileService.class.getSimpleName(), getStaticModel(CodeFileService.class));
        templateModel.put("project", takeProject(sysEntity.getClazzName()));
        templateModel.put("basepackage", takeBasepackage(sysEntity.getClazzName()));
        templateModel.put("className", takeSimpleClassName(sysEntity.getClazzName()));
        templateModel.put("classNameUncap", StringUtils.uncapitalize(takeSimpleClassName(sysEntity.getClazzName())));
        templateModel.put("modelName", takeSimpleClassName(sysEntity.getClazzName()) + "Model");
        templateModel.put("createTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd'T'HH:mm:ssZZ"));
        templateModel.put("viewFolder", takeSimpleClassName(sysEntity.getClazzName()).toLowerCase());
        templateModel.put("laputaKendoRequire", takeLaputaKendoRequire(sysEntity));
        templateModel.put("referenceSysEntityList", referenceSysEntityList);
        templateModel.put("nestClazzNameMap", nestClazzNameMap);

        generator.generateBy(templateModel, templateModel);
    }

    private Object getStaticModel(Class clz) {
        //BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        BeansWrapper wrapper = new BeansWrapperBuilder(new Version("2.3.23")).build();
        try {
            return wrapper.getStaticModels().get(clz.getName());
        } catch (TemplateModelException e) {
            //ignore
        }
        return null;
    }

    public static String takeProject(String className) {
        int rightPos = className.indexOf(".entity.");
        if (rightPos < 0) {
            throw new RuntimeException("不合规的实体类名 : " + className);
        }

        String leftSub = className.substring(0, rightPos);
        return leftSub.substring(leftSub.lastIndexOf(".") + 1);
    }

    public static String takeLaputaKendoRequire(SysEntity sysEntity) {
        String require = "";
        for (SysField sysField : sysEntity.getSysFieldCollection()) {
            if (ColumnType.ONE_TO_MANY.name().equalsIgnoreCase(sysField.getColumnTypeCode())) {
                if (require.indexOf("kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes") < 0) {
                    require = require.concat(",").concat("\"kendo/third/LaputaTreeViewCheckboxes/laputa.treeview.checkboxes\"");
                }
            } else if (ColumnType.MANY_TO_ONE.name().equalsIgnoreCase(sysField.getColumnTypeCode())) {
                if (require.indexOf("kendo/third/dropdowntreeview/laputa.dropdown.treelist") < 0) {
                    require = require.concat(",").concat("\"kendo/third/dropdowntreeview/laputa.dropdown.treelist\"");
                }
            } else if (ColumnType.ONE_TO_MANY.name().equalsIgnoreCase(sysField.getColumnTypeCode())) {
                if (require.indexOf("kendo/third/dropdowntreeview/laputa.dropdown.treelist") < 0) {
                    require = require.concat(",").concat("\"kendo/third/dropdowntreeview/laputa.dropdown.treelist\"");
                }
            } else if (ColumnType.COLUMN.name().equalsIgnoreCase(sysField.getColumnTypeCode())) {
                if (TemporalType.TIMESTAMP.name().equals(sysField.getTemporalType())) {
                    if (require.indexOf("kendo/js/kendo.datetimepicker") < 0) {
                        require = require.concat(",").concat("\"kendo/js/kendo.datetimepicker\"");
                    }
                }
            }
        }

        return require;
    }

    public static String takeBasepackage(String className) {
        int rightPos = className.indexOf(".entity.");
        if (rightPos < 0) {
            throw new RuntimeException("不合规的实体类名 : " + className);
        }
        return className.substring(0, rightPos);
    }

    public static String takeSimpleClassName(String className) {
        int leftPos = className.lastIndexOf(".");
        if (leftPos < 0) {
            throw new RuntimeException("不合规的实体类名 : " + className);
        }
        return className.substring(leftPos + 1);
    }

    public String getOutRootDir() {
        return outRootDir;
    }

    public void setOutRootDir(String outRootDir) {
        this.outRootDir = outRootDir;
    }
}
