package com.bupt.microbootspring;

import com.bupt.microbootspring.config.BaseConfig;
import com.bupt.microbootspring.model.Staff;
import com.bupt.microbootspring.model.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = "com.bupt")
//@EnableAutoConfiguration
public class MicrobootSpringApplication {

    public static void main(String[] args) {
        // 1. 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MicrobootSpringApplication.class, args);
        // 2. 查看容器的组件: 主程序所在的包及其子包的组件都能扫描到;
        String[] names = run.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

        Staff staff01 = run.getBean("jason", Staff.class);
        Staff staff02 = run.getBean("jason", Staff.class);
        System.out.println("验证组件是否单例: " + (staff01 == staff02));

        // 配置类本身也是组件;
        BaseConfig config = run.getBean(BaseConfig.class);
        System.out.println(config);

        User user01 = config.user1();
        User user02 = config.user1();
        System.out.println("验证通过调用方法构造的bean是否单例: " + (user01 == user02));

        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        Arrays.stream(beanNamesForType).forEach(System.out::println);

        System.out.println("容器中jason组件: " + run.containsBean("jason"));
        System.out.println("容器中user01组件: " + run.containsBean("user01"));
        System.out.println("容器中jason2组件: " + run.containsBean("jason2"));
    }

}
