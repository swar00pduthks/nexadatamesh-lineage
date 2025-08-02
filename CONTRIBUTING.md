# Contributing to Nexa Lineage

Thank you for your interest in contributing to Nexa Lineage! This document provides guidelines and standards for contributing to the project.

## 🚀 Quick Start

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/your-feature-name`
3. **Make your changes**
4. **Run tests**: `mvn test`
5. **Format code**: `mvn spotless:apply`
6. **Commit changes**: `git commit -m "feat: your feature description"`
7. **Push to your fork**: `git push origin feature/your-feature-name`
8. **Create a Pull Request**

## 📋 Pull Request Standards

### 🎯 PR Template
We use a comprehensive PR template that includes:

- **Type of Change**: Bug fix, feature, breaking change, etc.
- **Testing Checklist**: Unit tests, integration tests, manual testing
- **Code Quality**: Spotless, PMD, documentation
- **Security**: Vulnerability checks, input validation
- **Deployment**: Docker builds, Kubernetes manifests
- **Review Checklist**: For reviewers to follow

### ✅ Required Checks
All PRs must pass:

- **Code Quality Checks**: Spotless, PMD, JaCoCo
- **Build and Test**: Maven build, unit tests, integration tests
- **Build and Deploy**: Docker image builds (for main/develop)

### 🔍 Review Process
1. **Self-review**: Complete the checklist in the PR template
2. **Code review**: At least one other developer must review
3. **CI/CD checks**: All automated checks must pass
4. **Documentation**: Update docs if needed
5. **Testing**: Ensure all tests pass

## 🏗️ Development Standards

### 📝 Code Style
- **Java**: Google Java Format (enforced by Spotless)
- **TypeScript**: Prettier configuration
- **Naming**: Follow Java/TypeScript conventions
- **Comments**: Document complex logic
- **Error Handling**: Proper exception handling

### 🧪 Testing Standards
- **Unit Tests**: Minimum 80% code coverage
- **Integration Tests**: Database and API tests
- **Test Naming**: Descriptive test method names
- **Test Data**: Use test fixtures, not hardcoded data
- **Mocking**: Mock external dependencies

### 🔒 Security Standards
- **Input Validation**: Validate all user inputs
- **Authentication**: Implement proper auth where needed
- **Authorization**: Check permissions appropriately
- **Secrets**: Never commit secrets to Git
- **Dependencies**: Keep dependencies updated

### 🐳 Docker Standards
- **Multi-stage builds**: Optimize image size
- **Security**: Use non-root users
- **Caching**: Optimize layer caching
- **Health checks**: Include health check endpoints
- **Documentation**: Document environment variables

## 🚀 CI/CD Pipeline

### 📊 Code Quality Tools
- **Spotless**: Code formatting and import ordering
- **PMD**: Static code analysis
- **JaCoCo**: Code coverage reporting
- **GitHub Actions**: Automated CI/CD

### 🐳 Docker Integration
- **Build**: Multi-stage Docker builds
- **Push**: Automatic push to Docker Hub
- **Tags**: Latest and commit SHA tags
- **Cache**: GitHub Actions cache optimization

### 🏗️ Deployment
- **Kubernetes**: Helm charts for deployment
- **Environment**: Separate dev/staging/prod configs
- **Rollback**: Automated rollback capabilities
- **Monitoring**: Health checks and metrics

## 📚 Documentation Standards

### 📝 Code Documentation
- **JavaDoc**: Document public APIs
- **README**: Project overview and setup
- **API Docs**: OpenAPI/Swagger documentation
- **Architecture**: System design documentation

### 🎯 User Documentation
- **Installation**: Clear setup instructions
- **Configuration**: Environment variables and settings
- **Troubleshooting**: Common issues and solutions
- **Examples**: Code examples and use cases

## 🔧 Development Environment

### 🛠️ Local Setup
```bash
# Clone repository
git clone https://github.com/your-username/nexadatamesh-lineage.git
cd nexa-lineage

# Start databases
cd development
docker-compose up -d

# Build and run API
cd api
mvn spring-boot:run

# Build and run UI
cd web
npm run dev
```

### 🌐 Gitpod Setup
- **One-click setup**: Open in Gitpod
- **Pre-configured**: All tools and databases ready
- **Collaborative**: Real-time collaboration
- **Cloud-based**: No local setup required

## 🎯 Commit Standards

### 📝 Commit Message Format
```
type(scope): description

[optional body]

[optional footer]
```

### 🏷️ Types
- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting, etc.)
- **refactor**: Code refactoring
- **test**: Adding or updating tests
- **chore**: Maintenance tasks

### 📝 Examples
```
feat(api): add lineage graph endpoint
fix(web): resolve favicon display issue
docs(readme): update installation instructions
test(api): add integration tests for dataset service
```

## 🔍 Review Guidelines

### 📋 For Contributors
- **Self-review**: Complete the PR checklist
- **Test locally**: Ensure everything works
- **Document changes**: Update relevant docs
- **Respond to feedback**: Address review comments

### 👀 For Reviewers
- **Code quality**: Check readability and structure
- **Security**: Look for vulnerabilities
- **Testing**: Ensure adequate test coverage
- **Documentation**: Verify docs are updated
- **Performance**: Check for performance issues

## 🚨 Breaking Changes

### 📋 Guidelines
- **Semantic versioning**: Follow semver principles
- **Migration guide**: Provide upgrade instructions
- **Deprecation warnings**: Give advance notice
- **Backward compatibility**: Maintain when possible

### 📝 Process
1. **Create issue**: Document the breaking change
2. **Update docs**: Migration guide and changelog
3. **Version bump**: Increment major version
4. **Communication**: Notify stakeholders

## 📊 Metrics and Quality

### 🎯 Quality Metrics
- **Code coverage**: Minimum 80%
- **Technical debt**: Track and reduce
- **Performance**: Monitor response times
- **Security**: Regular vulnerability scans

### 📈 Performance Standards
- **API response**: < 200ms for simple operations
- **Database queries**: Optimized and indexed
- **Memory usage**: Efficient resource utilization
- **Scalability**: Horizontal scaling support

## 🤝 Community Guidelines

### 💬 Communication
- **Be respectful**: Treat others with respect
- **Be constructive**: Provide helpful feedback
- **Be inclusive**: Welcome diverse perspectives
- **Be patient**: Allow time for responses

### 🎯 Contribution Areas
- **Code**: Bug fixes and new features
- **Documentation**: Improve docs and examples
- **Testing**: Add tests and improve coverage
- **Infrastructure**: CI/CD and deployment
- **Design**: UI/UX improvements

## 📞 Getting Help

### 🆘 Support Channels
- **GitHub Issues**: Bug reports and feature requests
- **Discussions**: General questions and ideas
- **Documentation**: Self-service help
- **Community**: Connect with other contributors

### 📚 Resources
- [GitHub Issues](https://github.com/your-org/nexadatamesh-lineage/issues)
- [Project Documentation](https://github.com/your-org/nexadatamesh-lineage/docs)
- [API Documentation](http://localhost:8080/api/swagger-ui.html)
- [Contributing Guide](CONTRIBUTING.md)

---

Thank you for contributing to Nexa Lineage! 🚀 