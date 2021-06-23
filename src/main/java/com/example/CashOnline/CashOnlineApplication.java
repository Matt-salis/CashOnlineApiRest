package com.example.CashOnline;

import com.example.CashOnline.models.Loans;
import com.example.CashOnline.models.User;
import com.example.CashOnline.repositories.LoansRepository;
import com.example.CashOnline.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class CashOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashOnlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner initPlayers(UserRepository userRepository, LoansRepository loansRepository) {
		return (args) -> {
			User user1 = new User("test@app.com.ar", "pepe", "argento" );

			Loans loan1 = new Loans(2500.00, user1);
			Loans loan2 = new Loans(65120.75, user1);

			userRepository.save(user1);

			loansRepository.save(loan1);
			loansRepository.save(loan2);
		};
	}

}
