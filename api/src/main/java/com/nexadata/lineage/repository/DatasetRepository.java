package com.nexadata.lineage.repository;

import com.nexadata.lineage.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {
    
    List<Dataset> findByNamespace(String namespace);
    
    Optional<Dataset> findByNameAndNamespace(String name, String namespace);
    
    boolean existsByNameAndNamespace(String name, String namespace);
} 