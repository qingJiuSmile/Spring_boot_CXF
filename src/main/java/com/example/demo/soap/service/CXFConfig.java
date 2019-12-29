package com.example.demo.soap.service;


import com.example.demo.soap.service.rs.UserServiceRS;
import com.example.demo.soap.service.ws.UserServiceWS;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.Arrays;

/**
 * Created by mabo-pc on 2017/7/9.
 */
@Configuration
public class CXFConfig {

	@Autowired
	private UserServiceWS userServiceWS;

	@Autowired
	private UserServiceRS userServiceRS;

	/**
	 * 注入servlet  bean name不能dispatcherServlet 否则会覆盖dispatcherServlet
	 * @return
	 */
	@Bean(name = "cxfServlet")
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(),"/myWebservice/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	//JAX-WS发布
	@Bean
	public Endpoint WSServer() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userServiceWS);
		endpoint.publish("/UserServiceWS");
		return endpoint;
	}
	//JAX-RS发布
	@Bean
	public Server restfullServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(springBus());
		endpoint.setAddress("/UserServiceRS");
		endpoint.setServiceBeans(Arrays.<Object>asList(userServiceRS));
		endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
		return endpoint.create();
	}
}
