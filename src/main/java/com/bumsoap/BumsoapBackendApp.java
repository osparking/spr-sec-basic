package com.bumsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

//@formatter:off
@SpringBootApplication
@EnableMethodSecurity(
    prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class BumsoapBackendApp {

  public static void main(String[] args) {
    SpringApplication.run(BumsoapBackendApp.class, args);
  }

}
