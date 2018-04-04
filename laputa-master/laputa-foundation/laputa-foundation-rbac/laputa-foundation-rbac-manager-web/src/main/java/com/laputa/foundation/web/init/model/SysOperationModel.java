package com.laputa.foundation.web.init.model;

import java.io.Serializable;
import java.util.List;

import com.laputa.foundation.web.rbac.entity.SysOperation;

/**
 * 初始化使用, 默认配置二级,主要协作Controller
 * Created by JiangDongPing on 2017/1/9.
 */
public class SysOperationModel implements Serializable {
    private SysOperation sysOperation;

    private List<SysOperation> childSysOperation;

    public SysOperation getSysOperation() {
        return sysOperation;
    }

    public void setSysOperation(SysOperation sysOperation) {
        this.sysOperation = sysOperation;
    }

    public List<SysOperation> getChildSysOperation() {
        return childSysOperation;
    }

    public void setChildSysOperation(List<SysOperation> childSysOperation) {
        this.childSysOperation = childSysOperation;
    }
}
