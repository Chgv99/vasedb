package com.santobucle.VaseDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VaseDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaseDbApplication.class, args);
	}

}
