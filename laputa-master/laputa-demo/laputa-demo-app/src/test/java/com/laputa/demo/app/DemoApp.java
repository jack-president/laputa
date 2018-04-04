package com.laputa.demo.app;

import com.laputa.foundation.app.LaputaSpringApp;

public class DemoApp {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.remove();

        LaputaSpringApp.main(args);
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
