# ParkControl - Backend

Sistema de gestión de estacionamientos para condominios. Backend en Spring Boot que actúa como BFF (Backend-for-Frontend) entre el frontend React y la API Central de CondoSaaS, más una base de datos PostgreSQL en Neon para datos complementarios.

## Arquitectura

```
Frontend (React)
    ↓
CloudFront → ALB → ECS Fargate (Spring Boot BFF)
                        ↓
              ┌──────────────────────┐
              ↓                      ↓
    API Central (Render)       Neon (PostgreSQL)
    - Estacionamientos         - PropietariosPlaza
    - Vehículos                - PrestamosPlaza
    - Permanencias             - PasesInvitados
    - Pases Invitados
    - Usuarios
```

## Tecnologías

- Java 21, Spring Boot 3.4.4
- PostgreSQL (Neon)
- JPA / Hibernate
- Docker, ECS Fargate
- Gradle/Maven

## Módulos

| Módulo | Responsabilidad |
|--------|----------------|
| `controller/` | Endpoints REST |
| `service/` | Lógica de negocio + Stores locales |
| `provider/` | Abstracción de API Central |
| `client/` | Comunicación HTTP con API Central |
| `model/` | Modelos de dominio |
| `entity/` | Entidades JPA (Neon) |
| `repository/` | Repositorios JPA |
| `dto/` | Request DTOs |
| `config/` | Configuración (seguridad, JWT, CORS) |

## Variables de Entorno

| Variable | Descripción | Default |
|----------|-------------|---------|
| `PORT` | Puerto del servidor | 8080 |
| `EXTERNAL_API_BASE_URL` | URL de la API Central | https://parking-system-backend-0chy.onrender.com |
| `DB_URL` | URL de Neon PostgreSQL | — |
| `DB_USER` | Usuario BD | — |
| `DB_PASSWORD` | Contraseña BD | — |
| `JWT_SECRET` | Secreto JWT | (default) |
| `JWT_EXPIRATION_MS` | Expiración token acceso | 28800000 (8h) |

## Ejecución Local

```bash
export DB_URL=jdbc:postgresql://...
export DB_USER=user
export DB_PASSWORD=pass
mvn spring-boot:run
```

## Despliegue

El backend se despliega en AWS ECS Fargate con imagen Docker. Ver `docs/deployment.md`.
