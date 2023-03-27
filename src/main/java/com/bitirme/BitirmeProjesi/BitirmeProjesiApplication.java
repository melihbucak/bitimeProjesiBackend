package com.bitirme.BitirmeProjesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class BitirmeProjesiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitirmeProjesiApplication.class, args);
	}

}
