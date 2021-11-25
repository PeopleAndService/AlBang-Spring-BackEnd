package com.pns.albangwas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AlbangWasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbangWasApplication.class, args);
	}

}
