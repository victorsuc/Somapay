package com.somapay.contaBancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.somapay")
@EntityScan("com.somapay")
@SpringBootApplication
public class ContaBancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaBancariaApplication.class, args);
	}

}
