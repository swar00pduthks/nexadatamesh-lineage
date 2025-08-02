package com.nexadata.lineage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexadata.lineage.entity.Namespace;
import com.nexadata.lineage.service.NamespaceService;

@RestController
@RequestMapping("/namespaces")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class NamespaceController {

  @Autowired private NamespaceService namespaceService;

  @GetMapping
  public ResponseEntity<List<Namespace>> getAllNamespaces() {
    List<Namespace> namespaces = namespaceService.getAllNamespaces();
    return ResponseEntity.ok(namespaces);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Namespace> getNamespaceById(@PathVariable Long id) {
    Optional<Namespace> namespace = namespaceService.getNamespaceById(id);
    return namespace.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Namespace> getNamespaceByName(@PathVariable String name) {
    Optional<Namespace> namespace = namespaceService.getNamespaceByName(name);
    return namespace.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Namespace> createNamespace(@RequestBody Namespace namespace) {
    try {
      Namespace createdNamespace = namespaceService.createNamespace(namespace);
      return ResponseEntity.ok(createdNamespace);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Namespace> updateNamespace(
      @PathVariable Long id, @RequestBody Namespace namespaceDetails) {
    try {
      Namespace updatedNamespace = namespaceService.updateNamespace(id, namespaceDetails);
      return ResponseEntity.ok(updatedNamespace);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNamespace(@PathVariable Long id) {
    try {
      namespaceService.deleteNamespace(id);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
