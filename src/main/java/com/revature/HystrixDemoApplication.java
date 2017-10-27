package com.revature;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope
@SpringBootApplication
@EnableHystrix
@RestController
@EnableDiscoveryClient
@EnableSwagger2
public class HystrixDemoApplication {
	
	@Value("${word : default}") String word;

	public static void main(String[] args) {
		SpringApplication.run(HystrixDemoApplication.class, args);
	}
	
	
	@RequestMapping("/hello")
	public String helloEndpoint() {
		return "hello +" + word;
	}
	
	
	@Bean
	public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().paths(regex("/.*"))
                .build();
             
    }
	
}
