package com.mars.india;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mars.india.service.PersonService;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MarsIndiaApplication{
	
	@Autowired
	PersonService personService;
	
	private static Logger LOG = LoggerFactory.getLogger(MarsIndiaApplication.class);

	public static void main(String[] args) {
			SpringApplication.run(MarsIndiaApplication.class, args);
		}
	
	@Bean
	public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
         .apis(RequestHandlerSelectors.basePackage("com.mars.india")).build();
	}


}
