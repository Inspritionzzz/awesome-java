package com.bupt.microbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MirobootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MirobootDemoApplication.class, args);
    }

}
