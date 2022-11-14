package com.bupt.awesomejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * @author jason
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AwesomeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomeJavaApplication.class, args);
		System.out.println("just a test");
	}
}
