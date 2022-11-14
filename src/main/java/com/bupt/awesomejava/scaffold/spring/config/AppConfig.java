package com.bupt.awesomejava.scaffold.spring.config;

import com.bupt.awesomejava.scaffold.spring.service.MyService;
import com.bupt.awesomejava.scaffold.spring.service.MyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
