package com.laputa.foundation.app;

import com.laputa.foundation.configurer.annotation.CloudValue;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class TestBean {

    private String normalIoc;

    @Value("${default.kkkkkkk:@}")
    private String kkkkkkk;

    private String propSomeValue;

    private String defaultKey01;

    public TestBean() {
        getClass();
    }

    @CloudValue("${testbean.cloud.ioc:@default012abc}")
    private String cloudIoc;

    public String getNormalIoc() {
        return normalIoc;
    }

    @Value("${testbean.normal.ioc:@}")
    public void setNormalIoc(String normalIoc) {
        this.normalIoc = normalIoc;
    }

    public String getCloudIoc() {
        return cloudIoc;
    }

    public void setCloudIoc(String cloudIoc) {
        this.cloudIoc = cloudIoc;
    }

    public void setPropSomeValue(String propSomeValue) {
        this.propSomeValue = propSomeValue;
    }

    public void setDefaultKey01(String defaultKey01) {
        this.defaultKey01 = defaultKey01;
    }
}
