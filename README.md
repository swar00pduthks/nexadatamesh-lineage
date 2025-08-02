# Nexa Lineage

Track data flow across your entire data ecosystem. Built with Spring Boot and Maven.

## 🏗️ Project Structure

```
nexa-lineage/
├── api/                    # Spring Boot API service
├── web/                    # Web UI (React/TypeScript)
├── chart/                  # Kubernetes Helm charts
├── docs/                   # Documentation
├── docker/                 # Docker configurations
└── examples/               # Example configurations
```

## 🚀 Quick Start

### Prerequisites

- Java 17
- Maven 3.8+
- Node.js 18+
- Docker
- Kubernetes cluster (optional)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/swar00pduthks/nexadatamesh-lineage.git
   cd nexa-lineage
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the API service**
   ```bash
   cd api
   mvn spring-boot:run
   ```

4. **Run the web UI**
   ```bash
   cd web
   npm install
   npm start
   ```

## 🐳 Docker

### Build images
```bash
docker build -t nexa-lineage-api:latest api/
docker build -t nexa-lineage-web:latest web/
```

### Run with Docker Compose
```bash
docker-compose up -d
```

## ☸️ Kubernetes

### Deploy with Helm
```bash
helm install nexa-lineage ./chart
```

## 📚 Documentation

- [API Documentation](docs/api.md)
- [Deployment Guide](docs/deployment.md)
- [Development Guide](docs/development.md)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.
