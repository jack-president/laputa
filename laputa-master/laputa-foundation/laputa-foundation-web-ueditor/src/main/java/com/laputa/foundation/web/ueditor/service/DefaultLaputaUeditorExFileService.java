package com.laputa.foundation.web.ueditor.service;

import com.laputa.foundation.web.ueditor.api.LaputaUeditorExFileService;
import com.laputa.foundation.web.ueditor.api.dto.UeditorExFileDTO;
import com.laputa.foundation.web.ueditor.controller.UeditorController;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jiangdongping on 2017/12/28 0028.
 */
@Service("defaultLaputaUeditorExFileService")
public class DefaultLaputaUeditorExFileService implements ServletContextAware {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLaputaUeditorExFileService.class);

    private ServletContext servletContext;

    @Autowired(required = false)
    private LaputaUeditorExFileService laputaUeditorExFileService;

    public UeditorExFileDTO saveFile(String originalFileName, byte[] fileData) {
        if (laputaUeditorExFileService != null) {
            return laputaUeditorExFileService.saveFile(originalFileName, fileData);
        } else {
            String savePath = servletContext.getRealPath("/static/ueditor/up");
            String fileName = UUID.randomUUID().toString();
            File file = new File(savePath + File.separator + fileName);
            try {
                FileUtils.writeByteArrayToFile(file, fileData);

                UeditorExFileDTO ueditorExFileDTO = new UeditorExFileDTO();
                ueditorExFileDTO.setOriginalFileName(originalFileName);
                ueditorExFileDTO.setType(takeExtFileName(originalFileName));
                ueditorExFileDTO.setSaveInDiskFileName(fileName);
                ueditorExFileDTO.setSize(fileData.length);
                ueditorExFileDTO.setUrl(servletContext.getContextPath() + "/static/ueditor/up/" + fileName);
                ueditorExFileDTO.setUpdateTime(new Date());
                return ueditorExFileDTO;
            } catch (IOException e) {
                logger.error("写附件时失败{}", e);
                throw new RuntimeException();
            }
        }
    }

    private String takeExtFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return "";
        } else {
            return fileName.substring(index);
        }
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
