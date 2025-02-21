package com.everysesac.backend.global.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .getConfiguration()
                .setFieldMatchingEnabled(true) // 필드 매칭 활성화
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) // PRIVATE 필드 접근 허용
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }
}
