package com.laputa.fates.web.model;

/**
 * <p>
 * Laputa项目 Model<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
public class LaputaConfigUpdateDTO {

    /**
     * 主键
     */
    private Long id;

    private String configValue;

    private String causeDescript;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getCauseDescript() {
        return causeDescript;
    }

    public void setCauseDescript(String causeDescript) {
        this.causeDescript = causeDescript;
    }
}