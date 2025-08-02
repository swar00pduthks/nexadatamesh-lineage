package com.nexadata.lineage.service;

import com.nexadata.lineage.entity.Dataset;
import com.nexadata.lineage.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DatasetService {
    
    @Autowired
    private DatasetRepository datasetRepository;
    
    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();
    }
    
    public List<Dataset> getDatasetsByNamespace(String namespace) {
        return datasetRepository.findByNamespace(namespace);
    }
    
    public Optional<Dataset> getDatasetById(Long id) {
        return datasetRepository.findById(id);
    }
    
    public Optional<Dataset> getDatasetByNameAndNamespace(String name, String namespace) {
        return datasetRepository.findByNameAndNamespace(name, namespace);
    }
    
    public Dataset createDataset(Dataset dataset) {
        if (datasetRepository.existsByNameAndNamespace(dataset.getName(), dataset.getNamespace())) {
            throw new RuntimeException("Dataset with name '" + dataset.getName() + "' already exists in namespace '" + dataset.getNamespace() + "'");
        }
        return datasetRepository.save(dataset);
    }
    
    public Dataset updateDataset(Long id, Dataset datasetDetails) {
        Optional<Dataset> optionalDataset = datasetRepository.findById(id);
        if (optionalDataset.isPresent()) {
            Dataset dataset = optionalDataset.get();
            dataset.setName(datasetDetails.getName());
            dataset.setNamespace(datasetDetails.getNamespace());
            dataset.setDescription(datasetDetails.getDescription());
            dataset.setType(datasetDetails.getType());
            dataset.setLocation(datasetDetails.getLocation());
            return datasetRepository.save(dataset);
        } else {
            throw new RuntimeException("Dataset not found with id: " + id);
        }
    }
    
    public void deleteDataset(Long id) {
        if (datasetRepository.existsById(id)) {
            datasetRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dataset not found with id: " + id);
        }
    }
    
    public boolean existsByNameAndNamespace(String name, String namespace) {
        return datasetRepository.existsByNameAndNamespace(name, namespace);
    }
} 