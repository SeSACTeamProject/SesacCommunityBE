package com.everysesac.backend.global.config;

import com.everysesac.backend.global.converter.PrettySqlFormatter;
import com.p6spy.engine.spy.P6SpyOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class P6spyConfig {

    @PostConstruct  // 반드시 추가
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(
                PrettySqlFormatter.class.getName()
        );
        P6SpyOptions.getActiveInstance().setLogMessageFormat(PrettySqlFormatter.class.getName());
    }
}
