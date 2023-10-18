package com.los;

import com.los.entity.MasterRole;
import com.los.entity.MasterUser;
import com.los.exception.InternalErrorException;
import com.los.repository.MasterRoleRepository;
import com.los.repository.MasterUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LosRegularMiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(LosRegularMiniApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			PasswordEncoder passwordEncoder,
			MasterRoleRepository masterRoleRepository,
			MasterUserRepository masterUserRepository
	) {
		return args -> {
			if (!masterRoleRepository.existsByRoleCode("ADMIN")) {
				MasterRole masterRole = new MasterRole();
				masterRole.setRoleCode("ADMIN");
				masterRole.setRoleName("Administrator");
				masterRole.setIsDeleted(false);

				masterRoleRepository.save(masterRole);
			}

			if (!masterRoleRepository.existsByRoleCode("EMPLOYEE")) {
				MasterRole masterRole = new MasterRole();
				masterRole.setRoleCode("EMPLOYEE");
				masterRole.setRoleName("Employee");
				masterRole.setIsDeleted(false);

				masterRoleRepository.save(masterRole);
			}

			if (!masterUserRepository.existsByUsername("user_admin")) {
				MasterUser masterUser = new MasterUser();
				masterUser.setUsername("user_admin");
				masterUser.setPassword(passwordEncoder.encode("password"));
				masterUser.setIsDeleted(false);

				masterRoleRepository
						.findByRoleCode("ADMIN")
						.ifPresentOrElse(
								masterUser::setMasterRole,
								InternalErrorException::new
						);

				masterUserRepository.save(masterUser);
			}

			if (!masterUserRepository.existsByUsername("user_employee")) {
				MasterUser masterUser = new MasterUser();
				masterUser.setUsername("user_employee");
				masterUser.setPassword(passwordEncoder.encode("password"));
				masterUser.setIsDeleted(false);

				masterRoleRepository
						.findByRoleCode("EMPLOYEE")
						.ifPresentOrElse(
								masterUser::setMasterRole,
								InternalErrorException::new
						);

				masterUserRepository.save(masterUser);
			}
		};
	}
}
