package com.bupt.microbootstu.config;

import com.bupt.microbootstu.bean.Employer;
import com.bupt.microbootstu.bean.Person;
import com.bupt.microbootstu.bean.Student;
import com.bupt.microbootstu.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.net.http.HttpClient;

//@ConditionalOnMissingClass(value="com.bupt.microbootstu.bean.Student") // 放在类级别，如果注解判断生效，则整个配置类才生效
//@Import(HttpClient.class)     // 给容器中放指定类型的组件，组件的名字默认是全类名
@EnableConfigurationProperties(Employer.class)  // 开启Employer组件的属性绑定，默认会吧这个组件放到容器中，一般用于导入第三方写好的组件进行属性绑定
                                                // 如果导入第三方包，即使组件上标注了@Component、@EnableConfigurationProperties，也没用。因为组件都扫描不进来；
@SpringBootConfiguration    //自定义配置类
// @Configuration  // 通用配置类使用此注解
public class AppConfig {

    /**
     * 1、组件默认是单实例；
     * @return
     */
    @Scope("prototype")
    @Bean("user01") // 替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
    public User user01(){
        var user = new User();
        user.setid(123);
        user.setName("jason");
        user.setAge(18);
        return user;
    }

    @ConditionalOnClass(name="com.bupt.microbootstu.bean.User" )    // 放在方法级别，单独对方法进行注解判断
    @Bean("user02") // 替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
    public User user02(){
        var user = new User();
        user.setid(123);
        user.setName("jams");
        user.setAge(19);
        return user;
    }

    @ConditionalOnMissingClass(value="com.bupt.microbootstu.bean.Student")
    @Bean("user03") // 替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
    public User user03(){
        var user = new User();
        user.setid(123);
        user.setName("curry");
        user.setAge(19);
        return user;
    }

    @ConditionalOnBean(value= Student.class)
    @Bean("user04") // 替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
    public User user04(){
        var user = new User();
        user.setid(123);
        user.setName("durant");
        user.setAge(19);
        return user;
    }

    @ConditionalOnBean(value= Student.class)
    @Bean("user05") // 替代以前的Bean标签。组件在容器中的名字默认是方法名，可以直接修改注解的值
    public User user05(){
        var user = new User();
        user.setid(123);
        user.setName("durant");
        user.setAge(19);
        return user;
    }

//    @ConfigurationProperties(prefix = "person")  // 绑定配置文件也可以放在方法上
    @Bean
    public Person person(){
        return new Person();
    }

}
