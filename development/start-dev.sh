#!/bin/bash

echo "🚀 Starting Nexa Lineage Development Environment..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker and try again."
    exit 1
fi

# Start the development services
echo "📦 Starting PostgreSQL, Neo4j, Kafka, and Zookeeper..."
docker-compose up -d

# Wait for services to be ready
echo "⏳ Waiting for services to be ready..."

# Wait for PostgreSQL
echo "🔍 Checking PostgreSQL..."
until docker exec nexa-lineage-postgresql pg_isready -U postgres; do
    echo "⏳ Waiting for PostgreSQL..."
    sleep 2
done
echo "✅ PostgreSQL is ready!"

# Wait for Neo4j
echo "🔍 Checking Neo4j..."
until curl -s http://localhost:7474 > /dev/null; do
    echo "⏳ Waiting for Neo4j..."
    sleep 2
done
echo "✅ Neo4j is ready!"

# Wait for Kafka
echo "🔍 Checking Kafka..."
until docker exec nexa-lineage-kafka kafka-topics --bootstrap-server localhost:9092 --list > /dev/null 2>&1; do
    echo "⏳ Waiting for Kafka..."
    sleep 2
done
echo "✅ Kafka is ready!"

echo ""
echo "🎉 Development environment is ready!"
echo ""
echo "📊 Services:"
echo "   PostgreSQL: localhost:5432 (user: postgres, password: password, db: nexa_lineage)"
echo "   Neo4j: http://localhost:7474 (user: neo4j, password: password)"
echo "   Kafka: localhost:9092"
echo ""
echo "🚀 Next steps:"
echo "   1. Start the API: cd api && mvn spring-boot:run"
echo "   2. Start the UI: cd web && npm start"
echo ""
echo "🛑 To stop: docker-compose down" 