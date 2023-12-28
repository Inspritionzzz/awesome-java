package com.bupt.microbootstu;

import com.bupt.microbootstu.bean.Employer;
import com.bupt.microbootstu.bean.Person;
import com.bupt.microbootstu.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicrobootStuApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MicrobootStuApplication.class, args);
        var ioc =  SpringApplication.run(MicrobootStuApplication.class, args);
//        for (String name : ioc.getBeanDefinitionNames()){
//            System.out.println(name);
//        }

//        String[] forType = ioc.getBeanNamesForType(User.class);
//        for (String s: forType){
//            System.out.println(s);
//        }
//
//        Object user01 = ioc.getBean("user01");
//        Object user02 = ioc.getBean("user01");
//
//        System.out.println(user01 == user02);

        Person person = ioc.getBean(Person.class);
        System.out.println(person.toString());

        Employer employer = ioc.getBean(Employer.class);
        System.out.println(employer.toString());


    }

}
