package com.example.CashOnline;

import com.example.CashOnline.models.Loans;
import com.example.CashOnline.models.User;
import com.example.CashOnline.repositories.LoansRepository;
import com.example.CashOnline.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CashOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashOnlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleUser(UserRepository userRepository, LoansRepository loansRepository) {
		return (args) -> {
			User user1 = new User("test@app.com.ar", "Pepe", "Argento" );
			User user2 = new User("prueba@app.com.ar", "Mario", "Santos" );

			Loans loan1 = new Loans(2500.00, user1);
			Loans loan2 = new Loans(65120.75, user1);
			Loans loan3 = new Loans(1550.00, user2);
			Loans loan4 = new Loans(1212.12, user2);

			userRepository.save(user1);
			userRepository.save(user2);

			loansRepository.save(loan1);
			loansRepository.save(loan2);
			loansRepository.save(loan3);
			loansRepository.save(loan4);
		};
	}

}
