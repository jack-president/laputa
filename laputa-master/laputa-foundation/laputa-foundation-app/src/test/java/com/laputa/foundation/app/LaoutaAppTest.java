package com.laputa.foundation.app;

import org.springframework.boot.ansi.AnsiOutput;

public class LaoutaAppTest {

    public static void main(String[] args) {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        LaputaSpringApp.main(args);
    }
}
