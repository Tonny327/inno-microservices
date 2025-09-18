# User Service

A microservice for managing users with card information support.

## 🚀 Quick Start

```bash
# Start all services
docker-compose up --build

# Stop services
docker-compose down
```

**That's it!** Application will be available at: http://localhost:8080/api

## Requirements
- Docker & Docker Compose

## Database Schema
- **users**: id, name, surname, birth_date, email
- **card_info**: id, user_id, number, holder, expiration_date
- Liquibase migrations run automatically

## Useful Commands

```bash
# View status
docker-compose ps

# View logs  
docker-compose logs -f user-service

# Connect to database
docker exec -it user-service-postgres psql -U tonny -d user_service_db

# Clean restart
docker-compose down -v && docker-compose up --build
```
