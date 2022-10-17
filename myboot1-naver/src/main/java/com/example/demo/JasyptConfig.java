package com.example.demo;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

//main 메소드 있으면 SPRING BOOT APP으로 실행 가능
//또는 main 메소드 있는 부트클래스가 실행시 같이 실행 가능
@Configuration
@EnableEncryptableProperties//application.properties의 항목 접근 
public class JasyptConfig {

	@Autowired
	Environment environment;
	
	/*@Value("${myvar}")
	String myvar;*/
	
    @Bean("jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
    	//System.out.println(System.getenv("JASYPT_ENCRYPTOR_PASSWORD"));
    	System.out.println(System.getenv("USER"));
    	//System.out.println("전체환경변수값 : "+System.getenv());
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv("USER")); 
        
        // 암호화 키. #jasypt.encryptor.password=root
        //config.setPassword(environment.getProperty("jasypt.encryptor.password"));
        //실행시 arguments 입력값으로 대신할 땐 이렇게
        
		//WINDOWS / linux 환경변수에 JASYPT_ENCRYPTOR_PASSWORD=1234 설정후 RUN
        //window테스트ok, linux테스트error
        //config.setPassword(myvar);
		
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
		System.out.println("===JasyptConfig실행===");
		//application.properties db항목 복호화 확인
		System.out.println(encryptor.decrypt("y7fPL5j7VBOYGHPuTeNJStPXIvpjETXUSq+0zbBengrkavxf15bFPA=="));
		System.out.println(encryptor.decrypt("FznE7zLQQJfJ/pR4OoqL7yAOkY2pApJMv+AlkxS9gIwCFPgPHRHpFmqPm9GEd5e7"));
		System.out.println(encryptor.decrypt("JBz7sw/U0ZItkhFe+6LjF6GJBWvIa0Ch"));
		System.out.println(encryptor.decrypt("r8wnjd8ntkKnbsKRVVtQ2jrVNbq65NqY"));
		
        return encryptor;
    }
 
}
/*
spring.datasource.driver-class-name=ENC(y7fPL5j7VBOYGHPuTeNJStPXIvpjETXUSq+0zbBengrkavxf15bFPA==)
spring.datasource.url=ENC(FznE7zLQQJfJ/pR4OoqL7yAOkY2pApJMv+AlkxS9gIwCFPgPHRHpFmqPm9GEd5e7)
spring.datasource.username=ENC(JBz7sw/U0ZItkhFe+6LjF6GJBWvIa0Ch)
spring.datasource.password=ENC(r8wnjd8ntkKnbsKRVVtQ2jrVNbq65NqY)
*/







