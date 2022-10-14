package com.example.demo;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
	
    @Bean("jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("1234"); 
        // 암호화 키. 
        
        //config.setPassword(environment.getProperty("jasypt.encryptor.password"));
        //실행시 arguments 입력값으로 대신할 땐 이렇게
        
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
		System.out.println("===JasyptConfig실행===");
		//application.properties db항목 복호화 확인
		System.out.println(encryptor.decrypt("Y8odRO/yYJgZRck+0FuHl0s0mETiYcqDiMvS2t2NGks6KY/ki44iEA=="));
		System.out.println(encryptor.decrypt("qS1d7uQ46YJIhLmqJMt7Q3Mmk+WCzGRLF2eYfKcgVaOxB5T0MSb0hEdPmqse4jIh"));
		System.out.println(encryptor.decrypt("i6bAP12YaeQvN3jID65+fTFLFY2fvCN/"));
		System.out.println(encryptor.decrypt("t74ep0wdHzoiyXikV4/HEiXF+khfCVBL"));
		
        return encryptor;
    }
 
}
/*
spring.datasource.driver-class-name=ENC(Y8odRO/yYJgZRck+0FuHl0s0mETiYcqDiMvS2t2NGks6KY/ki44iEA==)
spring.datasource.url=ENC(qS1d7uQ46YJIhLmqJMt7Q3Mmk+WCzGRLF2eYfKcgVaOxB5T0MSb0hEdPmqse4jIh)
spring.datasource.username=ENC(i6bAP12YaeQvN3jID65+fTFLFY2fvCN/)
spring.datasource.password=ENC(t74ep0wdHzoiyXikV4/HEiXF+khfCVBL)
*/






