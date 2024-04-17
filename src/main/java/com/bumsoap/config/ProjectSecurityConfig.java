package com.bumsoap.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {
    // @formatter:off
    http.cors(corsCustomizer -> corsCustomizer
          .configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(
                HttpServletRequest request) {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedOrigins(
                  Collections.singletonList("http://localhost:4200"));
              config.setAllowedMethods(Collections.singletonList("*"));
              config.setAllowCredentials(true);
              config.setAllowedHeaders(Collections.singletonList("*"));
              config.setMaxAge(3600L);
              return config;
            }
          }))
        .authorizeHttpRequests(requests -> requests
        .requestMatchers("/notices", "/contact", "/register")
          .permitAll()
        .requestMatchers("/myCards", "/myAccount", "/myBalance", "/myLoans",
            "/user")
          .authenticated())
        .formLogin(withDefaults())
        .httpBasic(withDefaults());
    return http.build();
    // @formatter:on
  }
}
