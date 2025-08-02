@echo off
echo 🚀 Starting Nexa Lineage Development Environment...

REM Check if Docker is running
docker info >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not running. Please start Docker and try again.
    pause
    exit /b 1
)

REM Start the development services
echo 📦 Starting PostgreSQL, Neo4j, Kafka, and Zookeeper...
docker-compose up -d

echo.
echo ⏳ Waiting for services to be ready...
echo 🔍 This may take a few minutes...

REM Wait for services to be ready
timeout /t 30 /nobreak >nul

echo.
echo 🎉 Development environment is ready!
echo.
echo 📊 Services:
echo    PostgreSQL: localhost:5432 (user: postgres, password: password, db: nexa_lineage)
echo    Neo4j: http://localhost:7474 (user: neo4j, password: password)
echo    Kafka: localhost:9092
echo.
echo 🚀 Next steps:
echo    1. Start the API: cd api ^&^& mvn spring-boot:run
echo    2. Start the UI: cd web ^&^& npm start
echo.
echo 🛑 To stop: docker-compose down
pause 