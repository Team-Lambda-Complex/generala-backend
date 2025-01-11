package com.lambda_complex.generala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeneralaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralaApplication.class, args);
	}

}
