package com.nexadata.lineage.graph;

import com.nexadata.lineage.entity.Dataset;
import com.nexadata.lineage.entity.Namespace;
import com.nexadata.lineage.repository.DatasetRepository;
import com.nexadata.lineage.repository.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
public class GraphService {

    @Autowired
    private NamespaceRepository namespaceRepository;

    @Autowired
    private DatasetRepository datasetRepository;

    /**
     * Sync all data from PostgreSQL to Neo4j
     */
    @Transactional
    public void syncToNeo4j() {
        try {
            System.out.println("Starting sync to Neo4j...");
            
            // For now, just log what we would sync
            List<Namespace> namespaces = namespaceRepository.findAll();
            List<Dataset> datasets = datasetRepository.findAll();
            
            System.out.println("Would sync " + namespaces.size() + " namespaces and " + datasets.size() + " datasets");
            
            // TODO: Implement actual Neo4j sync when we fix the API
            System.out.println("Sync completed (placeholder)");
            
        } catch (Exception e) {
            System.err.println("Sync failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get lineage graph data
     */
    public Map<String, Object> getLineageGraph() {
        try {
            List<Namespace> namespaces = namespaceRepository.findAll();
            List<Dataset> datasets = datasetRepository.findAll();
            
            // Create a simple graph representation
            Map<String, Object> graph = new HashMap<>();
            
            // Create nodes
            List<Map<String, Object>> nodes = new java.util.ArrayList<>();
            
            // Add namespace nodes
            for (Namespace namespace : namespaces) {
                Map<String, Object> node = new HashMap<>();
                node.put("id", "ns-" + namespace.getId());
                node.put("label", namespace.getName());
                node.put("type", "Namespace");
                node.put("namespace", namespace.getName());
                node.put("description", namespace.getDescription());
                nodes.add(node);
            }
            
            // Add dataset nodes
            for (Dataset dataset : datasets) {
                Map<String, Object> node = new HashMap<>();
                node.put("id", "ds-" + dataset.getId());
                node.put("label", dataset.getName());
                node.put("type", "Dataset");
                node.put("namespace", dataset.getNamespace());
                node.put("description", dataset.getDescription());
                nodes.add(node);
            }
            
            // Create relationships
            List<Map<String, Object>> relationships = new java.util.ArrayList<>();
            for (Dataset dataset : datasets) {
                Optional<Namespace> namespaceOpt = namespaceRepository.findByName(dataset.getNamespace());
                if (namespaceOpt.isPresent()) {
                    Map<String, Object> rel = new HashMap<>();
                    rel.put("source", "ds-" + dataset.getId());
                    rel.put("target", "ns-" + namespaceOpt.get().getId());
                    rel.put("type", "BELONGS_TO");
                    relationships.add(rel);
                }
            }
            
            graph.put("nodes", nodes);
            graph.put("relationships", relationships);
            
            return graph;
            
        } catch (Exception e) {
            System.err.println("Failed to get lineage graph: " + e.getMessage());
            return Map.of("nodes", List.of(), "relationships", List.of());
        }
    }

    /**
     * Get datasets by namespace
     */
    public List<Map<String, Object>> getDatasetsByNamespace(String namespaceName) {
        try {
            List<Dataset> datasets = datasetRepository.findByNamespace(namespaceName);
            List<Map<String, Object>> result = new java.util.ArrayList<>();
            
            for (Dataset dataset : datasets) {
                Map<String, Object> datasetMap = new HashMap<>();
                datasetMap.put("id", dataset.getId());
                datasetMap.put("name", dataset.getName());
                datasetMap.put("namespace", dataset.getNamespace());
                datasetMap.put("description", dataset.getDescription());
                datasetMap.put("type", dataset.getType());
                datasetMap.put("location", dataset.getLocation());
                result.add(datasetMap);
            }
            
            return result;
        } catch (Exception e) {
            System.err.println("Failed to get datasets by namespace: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Get namespace with its datasets
     */
    public Map<String, Object> getNamespaceWithDatasets(String namespaceName) {
        try {
            Optional<Namespace> namespaceOpt = namespaceRepository.findByName(namespaceName);
            if (!namespaceOpt.isPresent()) {
                return Map.of("namespace", Map.of(), "datasets", List.of());
            }
            Namespace namespace = namespaceOpt.get();
            List<Dataset> datasets = datasetRepository.findByNamespace(namespaceName);
            
            Map<String, Object> result = new HashMap<>();
            
            // Namespace info
            Map<String, Object> namespaceMap = new HashMap<>();
            namespaceMap.put("id", namespace.getId());
            namespaceMap.put("name", namespace.getName());
            namespaceMap.put("description", namespace.getDescription());
            namespaceMap.put("owner", namespace.getOwner());
            result.put("namespace", namespaceMap);
            
            // Datasets
            List<Map<String, Object>> datasetsList = new java.util.ArrayList<>();
            for (Dataset dataset : datasets) {
                Map<String, Object> datasetMap = new HashMap<>();
                datasetMap.put("id", dataset.getId());
                datasetMap.put("name", dataset.getName());
                datasetMap.put("description", dataset.getDescription());
                datasetMap.put("type", dataset.getType());
                datasetMap.put("location", dataset.getLocation());
                datasetsList.add(datasetMap);
            }
            result.put("datasets", datasetsList);
            
            return result;
        } catch (Exception e) {
            System.err.println("Failed to get namespace with datasets: " + e.getMessage());
            return Map.of("namespace", Map.of(), "datasets", List.of());
        }
    }

    /**
     * Auto-sync every 5 minutes
     */
    @Scheduled(fixedRate = 300000) // 5 minutes
    public void autoSync() {
        try {
            syncToNeo4j();
        } catch (Exception e) {
            // Log error but don't fail the application
            System.err.println("Auto-sync failed: " + e.getMessage());
        }
    }
} 