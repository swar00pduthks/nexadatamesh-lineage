package com.nexadata.lineage.service;

import com.nexadata.lineage.entity.Namespace;
import com.nexadata.lineage.repository.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NamespaceService {
    
    @Autowired
    private NamespaceRepository namespaceRepository;
    
    public List<Namespace> getAllNamespaces() {
        return namespaceRepository.findAll();
    }
    
    public Optional<Namespace> getNamespaceById(Long id) {
        return namespaceRepository.findById(id);
    }
    
    public Optional<Namespace> getNamespaceByName(String name) {
        return namespaceRepository.findByName(name);
    }
    
    public Namespace createNamespace(Namespace namespace) {
        if (namespaceRepository.existsByName(namespace.getName())) {
            throw new RuntimeException("Namespace with name '" + namespace.getName() + "' already exists");
        }
        return namespaceRepository.save(namespace);
    }
    
    public Namespace updateNamespace(Long id, Namespace namespaceDetails) {
        Optional<Namespace> optionalNamespace = namespaceRepository.findById(id);
        if (optionalNamespace.isPresent()) {
            Namespace namespace = optionalNamespace.get();
            namespace.setName(namespaceDetails.getName());
            namespace.setDescription(namespaceDetails.getDescription());
            namespace.setOwner(namespaceDetails.getOwner());
            return namespaceRepository.save(namespace);
        } else {
            throw new RuntimeException("Namespace not found with id: " + id);
        }
    }
    
    public void deleteNamespace(Long id) {
        if (namespaceRepository.existsById(id)) {
            namespaceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Namespace not found with id: " + id);
        }
    }
    
    public boolean existsByName(String name) {
        return namespaceRepository.existsByName(name);
    }
} 