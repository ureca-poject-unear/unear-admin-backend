package com.unear.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UnearAdminBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(UnearAdminBackendApplication.class, args);
  }

}
