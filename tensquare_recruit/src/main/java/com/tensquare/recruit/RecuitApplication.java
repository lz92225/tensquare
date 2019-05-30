package com.tensquare.recruit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
@SpringBootApplication
@MapperScan("com.tensquare.recruit.mapper")
public class RecuitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecuitApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
