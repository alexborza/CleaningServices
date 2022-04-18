package com.cleaning;

import com.cleaning.entity.*;
import com.cleaning.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.*;

import java.util.*;

@SpringBootApplication
public class CleaningServicesApplication implements CommandLineRunner {

	@Autowired
	private AdminRepository adminRepository;

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
		Optional<User> user = adminRepository.findByRole(ERole.ROLE_ADMIN);
		if(user.isPresent()){
			LOG.info("Admin account already exists. Nothing will be done.");
		} else {
			Admin admin = new Admin("admin", encoder.encode("admin"));
			admin.setRole(ERole.ROLE_ADMIN);
			adminRepository.save(admin);
		}
	}
}
