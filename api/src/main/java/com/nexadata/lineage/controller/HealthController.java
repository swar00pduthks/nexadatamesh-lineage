package com.nexadata.lineage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class HealthController {

  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("Nexa Lineage API is running!");
  }

  @GetMapping("/ready")
  public ResponseEntity<String> ready() {
    return ResponseEntity.ok("Nexa Lineage API is ready!");
  }
}
