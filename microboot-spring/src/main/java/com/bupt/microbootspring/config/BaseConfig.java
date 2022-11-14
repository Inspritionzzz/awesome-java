package com.bupt.microbootspring.config;

import com.bupt.microbootspring.model.Staff;
import com.bupt.microbootspring.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  1. proxyBeanMethods代理bean方法：
 *      proxyBeanMethods = true full模式,可解决组件依赖;
 *      proxyBeanMethods = false lite模式,不会检查bean是否存在容器中;
 *  2. @Import给容器中自动创建组件,调用无参构造器;
 */
@Import({User.class})
@Configuration(proxyBeanMethods = true)
//@ConditionalOnBean(name = "jason")
@ConditionalOnMissingBean(name = "jason2")
public class BaseConfig {

//    @ConditionalOnBean(name = "jason")
    @Bean
    public User user1() {
       User user = new User("jason", 18);
       user.setStaff(staff());
       return user;
    }
    @Bean(name = "jason")
    public Staff staff() {
        return new Staff(123, "sa");
    }
}
