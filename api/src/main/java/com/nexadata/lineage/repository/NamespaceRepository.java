package com.nexadata.lineage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexadata.lineage.entity.Namespace;

@Repository
public interface NamespaceRepository extends JpaRepository<Namespace, Long> {

  Optional<Namespace> findByName(String name);

  boolean existsByName(String name);
}
