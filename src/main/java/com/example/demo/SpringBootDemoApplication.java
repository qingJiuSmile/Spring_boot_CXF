package com.example.demo;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class SpringBootDemoApplication {


	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	// 配置一个对象与json转换的工具
	@Bean
	public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
		return new JacksonJaxbJsonProvider();
	}
}
