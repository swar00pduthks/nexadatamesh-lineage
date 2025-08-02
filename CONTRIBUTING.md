# Contributing to Nexa Lineage

Thank you for your interest in contributing to Nexa Lineage! This document provides guidelines for contributing to the project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Code Style](#code-style)
- [Testing](#testing)
- [Pull Request Process](#pull-request-process)
- [Reporting Issues](#reporting-issues)

## Code of Conduct

This project and its participants are governed by our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## Getting Started

1. **Fork the repository**
2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR_USERNAME/nexadatamesh-lineage.git
   cd nexadatamesh-lineage
   ```
3. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## Development Setup

### Prerequisites

- Java 17 or higher
- Maven 3.8.0 or higher
- Node.js 18 or higher (for web UI)
- Docker and Docker Compose (for local development)

### Local Development

1. **Start the development environment**
   ```bash
   cd development
   docker-compose up -d
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the API**
   ```bash
   mvn spring-boot:run -pl api
   ```

4. **Run the web UI**
   ```bash
   cd web
   npm install
   npm run dev
   ```

## Code Style

### Java Code

We use the following tools to maintain code quality:

- **Spotless**: Code formatting (Google Java Format)
- **PMD**: Static code analysis
- **JaCoCo**: Code coverage
- **Maven Enforcer**: Build rules

Run code quality checks:
```bash
mvn spotless:check
mvn pmd:check
mvn test jacoco:report
```

### TypeScript/JavaScript Code

- **ESLint**: Code linting
- **Prettier**: Code formatting
- **TypeScript**: Type checking

Run web code quality checks:
```bash
cd web
npm run lint
npm run type-check
```

## Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific module tests
mvn test -pl api

# Run with coverage
mvn test jacoco:report
```

### Writing Tests

- Write unit tests for all new functionality
- Aim for at least 80% code coverage
- Use descriptive test names
- Follow the AAA pattern (Arrange, Act, Assert)

## Pull Request Process

1. **Update documentation** if needed
2. **Add tests** for new functionality
3. **Ensure all tests pass**
4. **Update the changelog** if applicable
5. **Submit a pull request** with a clear description

### Pull Request Template

```markdown
## Description
Brief description of the changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests added/updated
```

## Reporting Issues

When reporting issues, please include:

- **Environment**: OS, Java version, Maven version
- **Steps to reproduce**: Clear, step-by-step instructions
- **Expected behavior**: What you expected to happen
- **Actual behavior**: What actually happened
- **Additional context**: Logs, screenshots, etc.

## Commit Message Guidelines

Use conventional commit format:

```
type(scope): description

[optional body]

[optional footer]
```

Types:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Test changes
- `chore`: Build/tool changes

## Questions?

If you have questions, please:

1. Check the [documentation](README.md)
2. Search existing [issues](https://github.com/swar00pduthks/nexadatamesh-lineage/issues)
3. Create a new issue if needed

Thank you for contributing to Nexa Lineage! 🚀 