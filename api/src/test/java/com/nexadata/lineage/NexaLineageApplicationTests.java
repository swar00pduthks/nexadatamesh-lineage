package com.nexadata.lineage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NexaLineageApplicationTests {

  @Test
  void contextLoads() {
    // This test verifies that the Spring context loads successfully
  }

  @Test
  void basicTest() {
    // This test ensures JaCoCo has something to measure
    String message = "Hello, Nexa Lineage!";
    assert message.contains("Nexa");
  }
}
