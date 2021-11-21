package com.example.restws;

import com.example.restws.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
//@EnableAutoConfiguration(
//		exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class RestfulWebServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Calendar cal = Calendar.getInstance();
//		cal.set(1998, 2, 2); // Assumes MM/dd/yyyy
//		// cal.getTime() returns a Date object
//		userRepository.save(new User("Dinesh", "dinesh@email.com", "password", cal.getTime()));
//		userRepository.save(new User("Kartik", "kartik@email.com", "12345", cal.getTime()));
	}
}
