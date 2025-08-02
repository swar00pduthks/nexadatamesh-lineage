package com.nexadata.lineage.repository;

import com.nexadata.lineage.entity.Namespace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NamespaceRepository extends JpaRepository<Namespace, Long> {
    
    Optional<Namespace> findByName(String name);
    
    boolean existsByName(String name);
} 