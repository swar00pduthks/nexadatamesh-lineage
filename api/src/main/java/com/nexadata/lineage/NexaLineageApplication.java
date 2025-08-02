package com.nexadata.lineage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NexaLineageApplication {

  public static void main(String[] args) {
    SpringApplication.run(NexaLineageApplication.class, args);
  }
}
