package com.bumsoap.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {
    // @formatter:off
    http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/notices", "/contact")
          .permitAll()
        .requestMatchers("/myCards", "/myAccount", "/myBalance", "/myLoans")
          .authenticated())
        .formLogin(withDefaults())
        .httpBasic(withDefaults());
    return http.build();
    // @formatter:on
  }
}
