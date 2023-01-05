package com.example.test;

import com.example.test.users.user;
import com.example.test.groups.group;
import com.example.test.users.userRepository;
import com.example.test.groups.groupRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	CommandLineRunner initUser(userRepository UserRepository, groupRepository GroupRepository){
		return args ->{

			user user1 = new user("mpdhimal", "Instructor", "mpdhimal@iastate.edu", "password");
			user user2 = new user("danh", "TA", "danh@iastate.edu", "Software");
			user user3 = new user("Timothy", "Student", "tim@iastate.edu", "Law");
			user user4 = new user("Kalpes", "Student", "kalpes@iastate.edu", "business");

			UserRepository.save(user1);
			UserRepository.save(user2);
			UserRepository.save(user3);
			UserRepository.save(user4);


			group group1 = new group("group1");


			group1.addUsers(UserRepository.findByEmail("mpdhimal@iastate.edu"));
			group1.addUsers(UserRepository.findByEmail("danh@iastate.edu"));
			group1.addUsers(UserRepository.findByEmail("tim@iastate.edu"));
			group1.addUsers(UserRepository.findByEmail("kalpes@iastate.edu"));

			GroupRepository.save(group1);

		};

	}
}
