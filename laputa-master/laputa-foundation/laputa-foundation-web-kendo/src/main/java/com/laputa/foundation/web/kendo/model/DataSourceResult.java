package com.laputa.foundation.web.kendo.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangDongPing on 2016/11/29.
 */
public class DataSourceResult {
    private Long total;

    private List<?> data;

    private Map<String, Object> aggregates;

    public DataSourceResult() {
        this.data = Collections.emptyList();
    }

    public DataSourceResult(List<?> data, long total) {
        this.total = total;
        if (data != null) {
            this.data = data;
        } else {
            this.data = Collections.emptyList();
        }
    }

    public DataSourceResult(List<?> data, long total, Map<String, Object> aggregates) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = Collections.emptyList();
        }
        this.total = total;
        this.aggregates = aggregates;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getAggregates() {
        return aggregates;
    }

    public void setAggregates(Map<String, Object> aggregates) {
        this.aggregates = aggregates;
    }
}
