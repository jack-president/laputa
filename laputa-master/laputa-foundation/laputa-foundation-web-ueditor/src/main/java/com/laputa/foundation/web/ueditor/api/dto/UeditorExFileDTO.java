package com.laputa.foundation.web.ueditor.api.dto;

import java.util.Date;

/**
 * Created by jiangdongping on 2017/12/28 0028.
 */
public class UeditorExFileDTO {
    /**
     * 上传时文件原名
     */
    private String originalFileName;

    /**
     * 文件类型 eg : ".png"
     */
    private String type;

    /**
     * 保存在服务端磁盘上的文件名
     */
    private String saveInDiskFileName;

    /**
     * http访问资源地址
     */
    private String url;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 上传时间
     */
    private Date updateTime;

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSaveInDiskFileName() {
        return saveInDiskFileName;
    }

    public void setSaveInDiskFileName(String saveInDiskFileName) {
        this.saveInDiskFileName = saveInDiskFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
