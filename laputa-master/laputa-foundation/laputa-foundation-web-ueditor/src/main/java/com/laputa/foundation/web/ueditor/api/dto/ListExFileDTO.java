package com.laputa.foundation.web.ueditor.api.dto;

import java.util.List;

/**
 * 已存储文件分页列表
 * Created by jiangdongping on 2017/12/28 0028.
 */
public class ListExFileDTO {
    private Integer start;
    private Integer total;

    private List<UeditorExFileDTO> dataList;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UeditorExFileDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<UeditorExFileDTO> dataList) {
        this.dataList = dataList;
    }
}
