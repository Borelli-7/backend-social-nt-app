package com.kaly7dev.socialntapp;

import com.kaly7dev.socialntapp.config.OpenApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(OpenApiConfiguration.class)
public class SocialNtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.kaly7dev.socialntapp.SocialNtAppApplication.class, args);
	}

}
