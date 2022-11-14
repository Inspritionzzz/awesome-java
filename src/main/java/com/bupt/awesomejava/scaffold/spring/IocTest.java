package com.bupt.awesomejava.scaffold.spring;

import com.bupt.awesomejava.scaffold.spring.service.MyServiceImpl;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class IocTest {
    public static void main(String[] args) {
        // getBeanByXml();
        // getBeanByGroovy();
        // getBeanAnotherMethod();
    }

    /**
     * 通过xml创建bean
     */
    public static void getBeanByXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/config/spring.xml");
        // MyServiceImpl myService = (MyServiceImpl)context.getBean("myserviceImpl");
        MyServiceImpl myService = context.getBean("myserviceImpl",MyServiceImpl.class);
        System.out.println(myService);
    }

    /**
     * 通过groovy文件创建bean,需要在pom导入依赖
     */
    public static void getBeanByGroovy() {
        ApplicationContext context = new GenericGroovyApplicationContext("/config/services.groovy");
        MyServiceImpl myService = (MyServiceImpl) context.getBean("myServiceImpl02");
        System.out.println(myService);
    }

    /**
     * 更灵活的读取配置文件的方法
     */
    public static void getBeanAnotherMethod() {
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("/config/services.xml");
        context.refresh();
//        new GroovyBeanDefinitionReader(context).loadBeanDefinitions("/config/services.groovy");
        // context.refresh();
    }

}
