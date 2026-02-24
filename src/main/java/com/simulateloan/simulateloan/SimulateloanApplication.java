package com.simulateloan.simulateloan;

import com.simulateloan.simulateloan.application.usecases.SimulateLoanUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimulateloanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulateloanApplication.class, args);
        String hello = "Hello World";
        System.out.println(hello);

       new SimulateLoanUseCase().run();
	}

}
