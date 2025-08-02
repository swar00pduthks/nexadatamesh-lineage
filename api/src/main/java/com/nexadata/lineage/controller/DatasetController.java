package com.nexadata.lineage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexadata.lineage.entity.Dataset;
import com.nexadata.lineage.service.DatasetService;

@RestController
@RequestMapping("/datasets")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class DatasetController {

  @Autowired private DatasetService datasetService;

  @GetMapping
  public ResponseEntity<List<Dataset>> getAllDatasets() {
    List<Dataset> datasets = datasetService.getAllDatasets();
    return ResponseEntity.ok(datasets);
  }

  @GetMapping("/namespace/{namespace}")
  public ResponseEntity<List<Dataset>> getDatasetsByNamespace(@PathVariable String namespace) {
    List<Dataset> datasets = datasetService.getDatasetsByNamespace(namespace);
    return ResponseEntity.ok(datasets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dataset> getDatasetById(@PathVariable Long id) {
    Optional<Dataset> dataset = datasetService.getDatasetById(id);
    return dataset.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{name}/namespace/{namespace}")
  public ResponseEntity<Dataset> getDatasetByNameAndNamespace(
      @PathVariable String name, @PathVariable String namespace) {
    Optional<Dataset> dataset = datasetService.getDatasetByNameAndNamespace(name, namespace);
    return dataset.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Dataset> createDataset(@RequestBody Dataset dataset) {
    try {
      Dataset createdDataset = datasetService.createDataset(dataset);
      return ResponseEntity.ok(createdDataset);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Dataset> updateDataset(
      @PathVariable Long id, @RequestBody Dataset datasetDetails) {
    try {
      Dataset updatedDataset = datasetService.updateDataset(id, datasetDetails);
      return ResponseEntity.ok(updatedDataset);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDataset(@PathVariable Long id) {
    try {
      datasetService.deleteDataset(id);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
