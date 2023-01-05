package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Phase1Application {

	public static void main(String[] args) {
		SpringApplication.run(Phase1Application.class, args);
	}
	
	
	@Bean
	CommandLineRunner initUser(userDatabase userDatabase) {
		return args ->{
			User user1 = new User("json117@website.org", "json", "password", "User");
			User user2 = new User("json259@website.org", "json1", "password", "User");
			User user3 = new User("json054@website.org", "json2", "password", "User");
			
			userDatabase.save(user1);
			userDatabase.save(user2);
			userDatabase.save(user3);
		};
	}

	
}
