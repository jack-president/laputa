package com.laputa.foundation.test;


import com.laputa.foundation.spring.config.LaputaRootConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = LaputaRootConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
public abstract class LaputaJunitTest {

    static {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
    }
}
