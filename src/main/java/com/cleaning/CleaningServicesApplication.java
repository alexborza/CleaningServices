package com.cleaning;

import com.cleaning.entity.*;
import com.cleaning.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CleaningServicesApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CleaningServicesApplication.class);

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CleaningServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.findAll().size() == 3){
			LOG.info("Roles already exists. Nothing will be done.");
		} else {
			Role userRole = new Role(0, ERole.ROLE_USER);
			Role employeeRole = new Role(0, ERole.ROLE_EMPLOYEE);
			Role adminRole = new Role(0, ERole.ROLE_ADMIN);
			roleRepository.saveAll(List.of(userRole, employeeRole, adminRole));
		}
	}
}
