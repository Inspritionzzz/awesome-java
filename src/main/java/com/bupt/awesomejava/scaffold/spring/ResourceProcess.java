package com.bupt.awesomejava.scaffold.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceProcess {
    public static void main(String[] args) {
        getResource();
    }

    /**
     * Spring内置资源实现:
     * UrlResource
     *
     * ClassPathResource
     *
     * FileSystemResource
     *
     * ServletContextResource
     *
     * InputStreamResource
     *
     * ByteArrayResource
     */

    public static void getResource() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/config/spring.xml");;
        Resource template = ctx.getResource("/config/spring.xml");
        System.out.println(template);
    }
}
