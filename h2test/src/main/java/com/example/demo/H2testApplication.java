package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Subscriber;


@SpringBootApplication
public class H2testApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2testApplication.class, args);
	}
	
}
