package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;
import com.laputa.foundation.persistence.entity.IdEntity;

import javax.persistence.*;

/**
 * 配置历史
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_config_history")
public class LaputaConfigHistory extends AuditingIdEntity {

    private String configValue;

    private String descript;

    private LaputaConfig parentLaputaConfig;

    private Long parentLaputaConfigId;

    @Column(name = "config_value")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Column(name = "descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_laputa_config_id")
    public LaputaConfig getParentLaputaConfig() {
        return parentLaputaConfig;
    }

    public void setParentLaputaConfig(LaputaConfig parentLaputaConfig) {
        this.parentLaputaConfig = parentLaputaConfig;
    }

    @Column(name = "parent_laputa_config_id", insertable = false, updatable = false)
    public Long getParentLaputaConfigId() {
        return parentLaputaConfigId;
    }

    public void setParentLaputaConfigId(Long parentLaputaConfigId) {
        this.parentLaputaConfigId = parentLaputaConfigId;
    }
}
