package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.audit.AuditingIdEntity;
import com.laputa.foundation.persistence.entity.IdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 配置历史
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_config_history")
public class LaputaConfigHistory  extends AuditingIdEntity {

    private String configValue;

    private String descript;


    private List<LaputaConfigHistoryBelongtoRelationLaputaConfig> configList;

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


    @OneToMany(mappedBy = "laputaConfigHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LaputaConfigHistoryBelongtoRelationLaputaConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(List<LaputaConfigHistoryBelongtoRelationLaputaConfig> configList) {
        this.configList = configList;
    }
}
