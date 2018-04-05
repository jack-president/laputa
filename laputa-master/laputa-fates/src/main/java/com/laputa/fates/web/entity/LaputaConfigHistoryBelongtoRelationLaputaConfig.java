package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;

import javax.persistence.*;

/**
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_config_history_belongto_relation_laputa_config", uniqueConstraints = {
        @UniqueConstraint(name = "UK_laputa_config_history_id_union_laputa_config_id", columnNames = {
                "laputa_config_history_id", "laputa_config_id"
        })
})
public class LaputaConfigHistoryBelongtoRelationLaputaConfig extends AuditingIdEntity {

    private Long laputaConfigHistoryId;

    private LaputaConfigHistory laputaConfigHistory;

    private Long laputaConfigId;

    private LaputaConfig laputaConfig;


    @Column(name = "laputa_config_history_id", insertable = false, updatable = false)
    public Long getLaputaConfigHistoryId() {
        return laputaConfigHistoryId;
    }

    public void setLaputaConfigHistoryId(Long laputaConfigHistoryId) {
        this.laputaConfigHistoryId = laputaConfigHistoryId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "laputa_config_history_id", updatable = false)
    public LaputaConfigHistory getLaputaConfigHistory() {
        return laputaConfigHistory;
    }

    public void setLaputaConfigHistory(LaputaConfigHistory laputaConfigHistory) {
        this.laputaConfigHistory = laputaConfigHistory;
    }

    @Column(name = "laputa_config_id", insertable = false, updatable = false)
    public Long getLaputaConfigId() {
        return laputaConfigId;
    }

    public void setLaputaConfigId(Long laputaConfigId) {
        this.laputaConfigId = laputaConfigId;
    }


    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "laputa_config_id", updatable = false)
    public LaputaConfig getLaputaConfig() {
        return laputaConfig;
    }

    public void setLaputaConfig(LaputaConfig laputaConfig) {
        this.laputaConfig = laputaConfig;
    }
}
