package com.laputa.fates.web.entity;

import com.laputa.foundation.persistence.code.CodeAbleIdEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 项目
 * Created by JiangDongPing on 2018/04/05.
 */
@Entity
@Table(name = "laputa_aplication")
public class LaputaAplication extends CodeAbleIdEntity {

    private List<LaputaConfig> configList;

    @OneToMany(mappedBy = "parentLaputaAplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<LaputaConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(List<LaputaConfig> configList) {
        this.configList = configList;
    }
}
