package com.los.config;

import com.los.repository.TokenRepository;
import com.los.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenRepository tokenRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    private final String[] WHITE_LIST = {
            "/v1/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(WHITE_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(this.authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/v1/auth/logout")
                        .addLogoutHandler(this.logoutHandler())
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return;
            }

            jwt = authHeader.substring(7);

            tokenRepository
                    .findByToken(jwt)
                    .ifPresent(token -> {
                        token.setIsRevoked(true);
                        tokenRepository.save(token);
                    });
        };
    }
}
