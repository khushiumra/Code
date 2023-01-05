package COMS309.Final;

import COMS309.Final.groups.group;
import COMS309.Final.groups.groupRepository;
import COMS309.Final.userIdentities.userIdentity;
import COMS309.Final.userIdentities.userIdentityRepository;
import COMS309.Final.users.user;
import COMS309.Final.users.userRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
public class FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	@Bean
	CommandLineRunner initUser(userRepository UserRepository, groupRepository GroupRepository, userIdentityRepository UserIdentityRepository) {
		return args -> {

		};
	}

	//http://localhost:8080/swagger-ui.html#/
	@Bean
	public Docket getAPIdocs() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}


}


