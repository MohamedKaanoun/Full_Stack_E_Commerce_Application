package com.backend.EcommerceProjectBackEnd;

import com.backend.EcommerceProjectBackEnd.model.Role;
import com.backend.EcommerceProjectBackEnd.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class EcommerceProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProjectBackEndApplication.class, args);
	}
	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
		};
	}
}
