package com.cleaning;

import com.cleaning.domain.users.*;
import com.cleaning.infrastructure.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

@SpringBootApplication
public class CleaningServicesApplication implements CommandLineRunner {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Autowired
	private PasswordEncoder encoder;
	private static final Logger LOG = LoggerFactory.getLogger(CleaningServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CleaningServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addAdminAccount();
	}

	private void addAdminAccount(){
		Optional<User> user = userJpaRepository.findByRole(Role.ADMIN);
		if(user.isPresent()){
			LOG.info("Admin account already exists. Nothing will be done.");
		} else {
			User admin = new Admin("admin", encoder.encode("admin"));
			userJpaRepository.save(admin);
		}
	}
}
