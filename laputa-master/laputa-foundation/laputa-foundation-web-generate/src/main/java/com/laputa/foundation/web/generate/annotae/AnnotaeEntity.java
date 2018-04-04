package com.laputa.foundation.web.generate.annotae;

import java.util.Map;

import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
public class AnnotaeEntity {
    String name = "";

    Class<? extends IdEntity> clazz;

    Integer pageSize = 20;

    String descript = "";

    Map<String, Annotae> fieldsAnnotaeMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends IdEntity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends IdEntity> clazz) {
        this.clazz = clazz;
    }

    public Map<String, Annotae> getFieldsAnnotaeMap() {
        return fieldsAnnotaeMap;
    }

    public void setFieldsAnnotaeMap(Map<String, Annotae> fieldsAnnotaeMap) {
        this.fieldsAnnotaeMap = fieldsAnnotaeMap;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
