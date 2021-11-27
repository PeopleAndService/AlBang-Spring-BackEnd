package com.pns.albangwas;

import com.pns.albangwas.common.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties({FileProperties.class})
@EnableJpaAuditing
@SpringBootApplication
public class AlbangWasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbangWasApplication.class, args);
	}

}
