package com.laputa.foundation.app;

import com.laputa.foundation.configurer.annotation.CloudValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class TestService {

    @Autowired
    private TestBean testBean;

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }
}
