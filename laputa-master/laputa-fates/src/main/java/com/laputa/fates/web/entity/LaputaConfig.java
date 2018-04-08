package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;
import com.laputa.foundation.persistence.code.NameAbleIdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 配置
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_config")
public class LaputaConfig extends NameAbleIdEntity {

    private String code;

    private String configValue;

    private Long parentLaputaAplicationId;

    private LaputaAplication parentLaputaAplication;

    private List<LaputaConfigHistory> configHistoryList;

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "config_value")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }


    @Column(name = "parent_laputa_aplication_id", insertable = false, updatable = false)
    public Long getParentLaputaAplicationId() {
        return parentLaputaAplicationId;
    }

    public void setParentLaputaAplicationId(Long parentLaputaAplicationId) {
        this.parentLaputaAplicationId = parentLaputaAplicationId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_laputa_aplication_id")
    public LaputaAplication getParentLaputaAplication() {
        return parentLaputaAplication;
    }

    public void setParentLaputaAplication(LaputaAplication parentLaputaAplication) {
        this.parentLaputaAplication = parentLaputaAplication;
    }


    @OneToMany(mappedBy = "parentLaputaConfig", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LaputaConfigHistory> getConfigHistoryList() {
        return configHistoryList;
    }

    public void setConfigHistoryList(List<LaputaConfigHistory> configHistoryList) {
        this.configHistoryList = configHistoryList;
    }


}
