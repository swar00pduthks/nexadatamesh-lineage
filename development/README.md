# Development Environment

This directory contains the development setup for Nexa Lineage.

## 🚀 Quick Start

### Prerequisites
- Docker and Docker Compose
- Java 17
- Maven 3.8+

### 1. Start Development Services

**Windows:**
```bash
cd development
.\start-dev.bat
```

**Linux/Mac:**
```bash
cd development
chmod +x start-dev.sh
./start-dev.sh
```

### 2. Start the API

```bash
cd api
mvn spring-boot:run
```

### 3. Start the Web UI

```bash
cd web
npm install
npm run dev
```

The web UI will be available at `http://localhost:3000`

### 4. Test the API

The API will be available at `http://localhost:8080/api`

**Health Check:**
```bash
curl http://localhost:8080/api/health
```

**Create a Namespace:**
```bash
curl -X POST http://localhost:8080/api/namespaces \
  -H "Content-Type: application/json" \
  -d '{
    "name": "default",
    "description": "Default namespace",
    "owner": "admin"
  }'
```

**Create a Dataset:**
```bash
curl -X POST http://localhost:8080/api/datasets \
  -H "Content-Type: application/json" \
  -d '{
    "name": "users",
    "namespace": "default",
    "description": "User data",
    "type": "table",
    "location": "s3://bucket/users"
  }'
```

## 📊 Services

| Service | URL | Credentials |
|---------|-----|-------------|
| PostgreSQL | localhost:5432 | user: postgres, password: password, db: nexa_lineage |
| Neo4j | http://localhost:7474 | user: neo4j, password: password |
| Kafka | localhost:9092 | - |
| API | http://localhost:8080/api | - |
| Web UI | http://localhost:3000 | - |

## 🛑 Stop Services

```bash
cd development
docker-compose down
```

## 📁 Directory Structure

```
development/
├── docker-compose.yml    # Development services
├── start-dev.sh         # Linux/Mac startup script
├── start-dev.bat        # Windows startup script
└── README.md           # This file
``` 