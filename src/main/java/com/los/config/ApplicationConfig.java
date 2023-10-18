package com.los.config;

import com.los.entity.MasterUser;
import com.los.repository.MasterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApplicationConfig {

    private final MasterUserRepository masterUserRepository;

    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (Objects.isNull(authentication) || authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }

            MasterUser currentUser = (MasterUser) authentication.getPrincipal();

            return Optional.of(currentUser.getId());
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            @Transactional
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return masterUserRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
