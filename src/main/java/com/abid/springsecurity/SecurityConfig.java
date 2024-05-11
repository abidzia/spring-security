package com.abid.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/api/test/**").permitAll() // no Authentication needed for test api's
					.anyRequest().authenticated()
				)
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults()).build();
	}
	
	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	  // Replace with your user details service implementation
	  UserDetails user = User.withUsername("user")
	    .password(passwordEncoder.encode("{noop}password")) // Use a password encoder!
	    .roles("USER")
	    .build();

	  UserDetails admin = User.withUsername("admin")
	    .password(passwordEncoder.encode("{noop}admin")) // Use a password encoder!
	    .roles("ADMIN")
	    .build();

	  return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
}