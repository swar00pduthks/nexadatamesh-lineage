package com.nexadata.lineage.controller;

import com.nexadata.lineage.graph.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/graph")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class GraphController {

    @Autowired
    private GraphService graphService;

    /**
     * Get the complete lineage graph
     */
    @GetMapping("/lineage")
    public ResponseEntity<Map<String, Object>> getLineageGraph() {
        try {
            Map<String, Object> graph = graphService.getLineageGraph();
            return ResponseEntity.ok(graph);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to retrieve lineage graph: " + e.getMessage()));
        }
    }

    /**
     * Get datasets by namespace using graph query
     */
    @GetMapping("/datasets/namespace/{namespaceName}")
    public ResponseEntity<List<Map<String, Object>>> getDatasetsByNamespace(@PathVariable String namespaceName) {
        try {
            List<Map<String, Object>> datasets = graphService.getDatasetsByNamespace(namespaceName);
            return ResponseEntity.ok(datasets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(List.of(Map.of("error", "Failed to retrieve datasets: " + e.getMessage())));
        }
    }

    /**
     * Get namespace with its datasets
     */
    @GetMapping("/namespace/{namespaceName}")
    public ResponseEntity<Map<String, Object>> getNamespaceWithDatasets(@PathVariable String namespaceName) {
        try {
            Map<String, Object> result = graphService.getNamespaceWithDatasets(namespaceName);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to retrieve namespace: " + e.getMessage()));
        }
    }

    /**
     * Manually trigger sync from PostgreSQL to Neo4j
     */
    @PostMapping("/sync")
    public ResponseEntity<Map<String, String>> syncToNeo4j() {
        try {
            graphService.syncToNeo4j();
            return ResponseEntity.ok(Map.of("message", "Sync completed successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Sync failed: " + e.getMessage()));
        }
    }

    /**
     * Get graph statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getGraphStats() {
        try {
            // This would typically query Neo4j for statistics
            // For now, return basic info
            return ResponseEntity.ok(Map.of(
                "message", "Graph statistics endpoint",
                "status", "available"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to get graph stats: " + e.getMessage()));
        }
    }
} 