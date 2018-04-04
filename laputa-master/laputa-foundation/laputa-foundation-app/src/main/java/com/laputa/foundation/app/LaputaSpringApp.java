package com.laputa.foundation.app;

import com.laputa.foundation.spring.config.LaputaRootConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

public class LaputaSpringApp {

    public static final String DEFAULT_SPRING_CONFIG = "classpath*:/laputa-config/springcontext/";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LaputaRootConfig.class);
        //context.publishEvent(new ContextStartedEvent(context));
        while (true) {
            try {
                context.getClass();
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
