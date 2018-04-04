package com.laputa.foundation.web.rbac.model;

import java.io.Serializable;
import java.util.List;

/**
 * 所有权限相关信息集合
 * Created by JiangDongPing on 2018/04/02.
 */
public class RbacDTO implements Serializable {
    private List<SysFileModel> sysFileList;
    private List<SysElementModel> sysElementList;
    private List<SysOperationModel> sysOperationList;

    public List<SysFileModel> getSysFileList() {
        return sysFileList;
    }

    public void setSysFileList(List<SysFileModel> sysFileList) {
        this.sysFileList = sysFileList;
    }

    public List<SysElementModel> getSysElementList() {
        return sysElementList;
    }

    public void setSysElementList(List<SysElementModel> sysElementList) {
        this.sysElementList = sysElementList;
    }

    public List<SysOperationModel> getSysOperationList() {
        return sysOperationList;
    }

    public void setSysOperationList(List<SysOperationModel> sysOperationList) {
        this.sysOperationList = sysOperationList;
    }
}
