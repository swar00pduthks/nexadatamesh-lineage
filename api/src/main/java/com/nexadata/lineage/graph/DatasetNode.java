package com.nexadata.lineage.graph;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Dataset")
public class DatasetNode {

  @Id private String id;

  @Property("name")
  private String name;

  @Property("namespace")
  private String namespace;

  @Property("description")
  private String description;

  @Property("type")
  private String type;

  @Property("location")
  private String location;

  @Property("createdAt")
  private LocalDateTime createdAt;

  @Property("updatedAt")
  private LocalDateTime updatedAt;

  // Constructors
  public DatasetNode() {}

  public DatasetNode(
      String id, String name, String namespace, String description, String type, String location) {
    this.id = id;
    this.name = name;
    this.namespace = namespace;
    this.description = description;
    this.type = type;
    this.location = location;
    this.createdAt = LocalDateTime.now();
  }

  // Getters and Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
