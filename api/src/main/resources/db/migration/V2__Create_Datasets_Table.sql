CREATE TABLE datasets (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    namespace VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(100),
    location TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX idx_datasets_namespace ON datasets(namespace);
CREATE INDEX idx_datasets_name_namespace ON datasets(name, namespace);
CREATE UNIQUE INDEX idx_datasets_unique_name_namespace ON datasets(name, namespace); 