package com.simulateloan.simulateloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SimulateloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulateloanApplication.class, args);
        String hello = "Hello World";
        System.out.println(hello);
	}

}
