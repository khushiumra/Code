package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class Application {
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
		
	}
	
	@Bean
	
	public CommandLineRunner commandLineRunner(ApplicationContext ctx)
	{
		return args ->
		{
			System.out.println("Let us inspect the beans from Spring Boot:");
			
			String[] nameBean = ctx.getBeanDefinitionNames();
			Arrays.sort(nameBean);
			
			for(String nameBeans : nameBean)
			{
				System.out.println(nameBeans);
			}
		};
	}
}