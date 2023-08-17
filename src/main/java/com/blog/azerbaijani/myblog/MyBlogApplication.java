package com.blog.azerbaijani.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = "com.blog.azerbaijani")
@EntityScan(basePackages = "com.blog.azerbaijani.entity")
@EnableJpaRepositories(basePackages = "com.blog.azerbaijani.repository")
//@ComponentScan(basePackages = {"com.blog.azerbaijani.security", "com.blog.azerbaijani.service"})
public class MyBlogApplication {


	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
	}

}
