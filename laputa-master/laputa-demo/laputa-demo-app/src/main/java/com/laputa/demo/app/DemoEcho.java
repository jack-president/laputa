package com.laputa.demo.app;

import com.laputa.foundation.configurer.listener.ConfListener;
import com.laputa.foundation.configurer.listener.ConfListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JiangDongPing on 2018/04/07.
 */
public class DemoEcho implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(DemoEcho.class);

    private static final long PERIOD = 1 * 1000;// 1秒钟


//    @Value("${some.k.a:@2222}")
//    private Long someValue1;

    @Value("${some.k.a:@2222}")
    private Double someValue2;

    @Value("${some.k.a:@2222}")
    private float someValue3;

    @Value("${some.k.b:@true}")
    private boolean someValue4;

    @Value("${some.k.b:@FALSE}")
    private Boolean someValue5;

    //@Value("${some.k.long:@555}")
    private Long someLongValue;

    private Long someLongValueA;

    @Value("${some.k.a:@当前默认值}")
    private String someValue;


    @Override
    public void afterPropertiesSet() throws Exception {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.error(" DEMO APP {}", someValue);
            }
        }, 0, PERIOD);

        ConfListenerFactory.addSourceListener("group-win10-book-pc", null, new ConfListener() {
            @Override
            public void onChange(String key, String preValue, String curValue) throws Exception {
                logger.error("监听到 组所有KEY {} {} {}", key, preValue, curValue);
            }
        });

        ConfListenerFactory.addSourceListener("group-win10-book-pc", "some.k.a", new ConfListener() {
            @Override
            public void onChange(String key, String preValue, String curValue) throws Exception {
                logger.error("监听到 组特定KEY {} {} {}", key, preValue, curValue);
            }
        });
    }

    public Long getSomeLongValue() {
        return someLongValue;
    }

    public void setSomeLongValue(Long someLongValue) {
        this.someLongValue = someLongValue;
    }

    public Long getSomeLongValueA() {
        return someLongValueA;
    }

    @Value("${some.k.long.a:@6666}")
    public void xxxxxxxxx(Long someLongValueA) {
        this.someLongValueA = someLongValueA;
    }
}
