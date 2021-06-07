package org.example.email.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmailServiceApplication.class, args);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
