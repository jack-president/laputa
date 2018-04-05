package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 配置
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_config")
public class LaputaConfig extends CodeAbleIdEntity {

    private String configValue;

    private List<LaputaConfigHistoryBelongtoRelationLaputaConfig> configHistoryList;

    @Column(name = "config_value")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }


    @OneToMany(mappedBy = "laputaConfig", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LaputaConfigHistoryBelongtoRelationLaputaConfig> getConfigHistoryList() {
        return configHistoryList;
    }

    public void setConfigHistoryList(List<LaputaConfigHistoryBelongtoRelationLaputaConfig> configHistoryList) {
        this.configHistoryList = configHistoryList;
    }
}
