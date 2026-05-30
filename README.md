# ParkControl Backend

Sistema de gestión de estacionamientos — Spring Boot 3 + Java 21 + JPA + PostgreSQL + JWT.

## Stack

- Java 21
- Spring Boot 3.4
- Spring Data JPA (Hibernate)
- Spring Security + JWT (jjwt)
- PostgreSQL
- Lombok
- Jakarta Validation
- Maven

## Ramas

| Rama | Módulo |
|------|--------|
| `dev` | Base: entidades JPA, repositorios, config seguridad, common |
| `feature/auth-usuarios` | Autenticación (register, login, refresh, profile) |
| `feature/vehiculos` | CRUD vehículos con filtros y paginación |
| `feature/estacionamiento-mapa` | Mapa de estacionamiento, zonas, plazas, disponibilidad |
| `feature/control-acceso` | Registro de entrada/salida, control de garita |
| `feature/historial-accesos` | Historial con filtros por fecha/placa/estado |
| `feature/pases-invitados` | Pases temporales con código único |
| `feature/permanencia-dashboard` | Dashboard con KPIs y permanencias activas |

## Requisitos

- Java 21
- PostgreSQL 14+
- Maven 3.9+ (o usar `mvnw`)

## Instalación

```bash
# 1. Crear base de datos PostgreSQL
createdb parkcontrol

# 2. Ejecutar migración (usa el script SQL)
psql -d parkcontrol -f ParkControl.sql

# 3. Configurar variables de entorno
export DATABASE_URL=jdbc:postgresql://localhost:5432/parkcontrol
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export JWT_SECRET=your-256-bit-secret

# 4. Ejecutar
./mvnw spring-boot:run
```

## Endpoints

### Auth (`/api/auth`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/auth/register` | Registrar usuario |
| POST | `/api/auth/login` | Iniciar sesión |
| POST | `/api/auth/refresh` | Refrescar token |
| POST | `/api/auth/logout` | Cerrar sesión |
| GET | `/api/auth/profile` | Perfil del usuario |

### Vehículos (`/api/vehicles`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/vehicles` | Listar (paginado, filtros) |
| GET | `/api/vehicles/{id}` | Detalle |
| POST | `/api/vehicles` | Crear |
| PATCH | `/api/vehicles/{id}` | Actualizar |
| DELETE | `/api/vehicles/{id}` | Eliminar (soft delete) |

### Estacionamiento (`/api/parking`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/parking/map` | Mapa completo |
| GET | `/api/parking/spaces` | Lista de plazas |
| PATCH | `/api/parking/spaces/{id}/status` | Cambiar estado |
| POST | `/api/parking/spaces/{id}/assign` | Asignar vehículo |

### Control de Acceso (`/api/access`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/access/entry` | Registrar entrada |
| POST | `/api/access/exit` | Registrar salida |
| GET | `/api/access/status/{placa}` | Estado por placa |

### Historial (`/api/history`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/history` | Listar historial (paginado, filtros) |
| GET | `/api/history/{id}` | Detalle |

### Pases Invitados (`/api/guest-pass`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/guest-pass` | Crear pase |
| GET | `/api/guest-pass/{id}` | Detalle |
| POST | `/api/guest-pass/validate` | Validar pase |

### Dashboard (`/api/dashboard`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/dashboard/summary` | Resumen KPIs |
| GET | `/api/dashboard/occupancy` | Ocupación detallada |
| GET | `/api/dashboard/permanence` | Permanencias activas |
