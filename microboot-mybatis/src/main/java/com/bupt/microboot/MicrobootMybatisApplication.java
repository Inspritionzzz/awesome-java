package com.bupt.microboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author jason
 */
@SpringBootApplication

public class MicrobootMybatisApplication {
    public static void main(String[] args) {
        System.out.println("just a test");
        SpringApplication.run(MicrobootMybatisApplication.class, args);
    }

}
