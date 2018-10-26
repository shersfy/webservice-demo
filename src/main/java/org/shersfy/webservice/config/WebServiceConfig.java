package org.shersfy.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.shersfy.webservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Bean
	public ServletRegistrationBean<CXFServlet> dispatcherServlet() {
		return new ServletRegistrationBean<>(new CXFServlet(), "/service/*");//发布服务名称
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return  new SpringBus();
	}


	@Bean
	public Endpoint endpoint() {
		//绑定要发布的服务
		EndpointImpl endpoint = new EndpointImpl(springBus(), userInfoService);
		endpoint.publish("/user"); //显示要发布的名称
		return endpoint;
	}
}
