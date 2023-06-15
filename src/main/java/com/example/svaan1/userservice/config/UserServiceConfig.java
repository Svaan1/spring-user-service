package com.example.svaan1.userservice.config;

import com.example.svaan1.userservice.converter.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    public UserConverter userConverter() {
        return new UserConverter();
    }
}
