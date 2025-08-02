# Gitpod Development Environment

This directory contains Gitpod-specific configurations for the Nexa Lineage project.

## 🚀 Quick Start

1. **Open in Gitpod**: Click the "Open in Gitpod" button on the repository page
2. **Wait for initialization**: Gitpod will automatically:
   - Install Java 17 and Node.js
   - Install Maven and npm dependencies
   - Start PostgreSQL and Neo4j databases
   - Configure VS Code extensions

## 🐳 Docker Services

The Gitpod environment includes:

### PostgreSQL
- **Port**: 5432
- **Database**: `nexa_lineage`
- **User**: `postgres`
- **Password**: `password`

### Neo4j
- **Browser**: http://localhost:7474
- **Bolt**: localhost:7687
- **User**: `neo4j`
- **Password**: `password`

## 🔧 Development Workflow

### Starting Services
```bash
# Start databases (automatic in Gitpod)
cd .gitpod
docker-compose up -d

# Start API
cd api
mvn spring-boot:run

# Start UI
cd web
npm run dev
```

### Accessing Services
- **API**: https://8080-{workspace-id}.{cluster-host}
- **UI**: https://3000-{workspace-id}.{cluster-host}
- **Neo4j**: https://7474-{workspace-id}.{cluster-host}

## 🛠️ Available Tools

### Code Quality
```bash
# Format code
mvn spotless:apply

# Run tests
mvn test

# Generate coverage report
mvn jacoco:report
```

### Database Management
```bash
# View database logs
docker-compose logs postgresql
docker-compose logs neo4j

# Access PostgreSQL
docker exec -it nexa-lineage-postgresql-gitpod psql -U postgres -d nexa_lineage

# Access Neo4j
docker exec -it nexa-lineage-neo4j-gitpod cypher-shell -u neo4j -p password
```

## 📁 Project Structure

```
.gitpod/
├── docker-compose.yml    # Gitpod-specific database setup
├── README.md            # This file
└── .gitpod.yml         # Gitpod workspace configuration
```

## 🔍 Troubleshooting

### Database Connection Issues
```bash
# Check if databases are running
docker-compose ps

# Restart databases
docker-compose restart

# View logs
docker-compose logs
```

### Port Access Issues
- Ensure ports are properly exposed in Gitpod
- Check firewall settings
- Verify service health checks

### Performance Issues
- Gitpod workspaces have resource limits
- Consider using local development for heavy tasks
- Monitor resource usage in Gitpod dashboard

## 🎯 Next Steps

1. **Explore the API**: Visit Swagger UI at `/api/swagger-ui.html`
2. **Test the UI**: Navigate to the React application
3. **Connect to Neo4j**: Use the browser interface
4. **Run Tests**: Execute the test suite
5. **Check Coverage**: Review JaCoCo reports

## 📚 Resources

- [Gitpod Documentation](https://www.gitpod.io/docs)
- [Docker Compose Reference](https://docs.docker.com/compose/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://reactjs.org/docs/) 