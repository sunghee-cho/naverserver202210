package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//for ssl
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.apache.catalina.connector.Connector;

/*  xml
 * <context:component-scan base-package=a.b.test">
 * @ComponentScan(basepackage="a.b.test")
 * 
 * 
 * */
@SpringBootApplication
//1. 스프링부트 시작 클래스 - tomcat 내장 시작(application.properties파일  server.port=8081)
@ComponentScan //현재 패키지 annotation인식
@ComponentScan(basePackages = "mycontroller")
@ComponentScan(basePackages = "upload")
@ComponentScan(basePackages = "board.spring.mybatis")
@ComponentScan(basePackages = "test")
// @Mapper 스캔설정
@MapperScan(basePackages ="board.spring.mybatis" )
@MapperScan(basePackages = "upload")


//for ssl
@RestController
public class Myboot1Application {

	public static void main(String[] args) {
    	System.out.println("부트 시작-"+System.getenv("USER"));
		SpringApplication.run(Myboot1Application.class, args);
		System.out.println("부트 시작 완료");
	}
	
	@Bean    
	public ServletWebServerFactory serveltContainer(){       
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();        
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());       
		return tomcat;    
	}    
	
	private Connector createStandardConnector(){        
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");        
		connector.setPort(8080);        
		return connector;    
	}
}
