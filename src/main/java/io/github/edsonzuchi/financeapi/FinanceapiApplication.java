package io.github.edsonzuchi.financeapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(
		scanBasePackages = {
			"io.github.edsonzuchi.financeapi"
		}
)
public class FinanceapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinanceapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}