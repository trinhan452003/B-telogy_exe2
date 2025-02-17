package biteology.project;

import biteology.project.config.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class})
public class BiteologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiteologyApplication.class, args);
	}

}
