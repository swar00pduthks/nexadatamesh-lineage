package com.nexadata.lineage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexadata.lineage.entity.Dataset;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Long> {

  List<Dataset> findByNamespace(String namespace);

  Optional<Dataset> findByNameAndNamespace(String name, String namespace);

  boolean existsByNameAndNamespace(String name, String namespace);
}
