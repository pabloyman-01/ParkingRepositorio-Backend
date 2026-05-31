# ParkControl Backend — Módulo Estructura

Sistema de gestión de estacionamientos. Backend que actúa como **gateway** entre el frontend y una API Central externa.

## Stack

- Java 21
- Spring Boot 3.4
- Spring Security + JWT (jjwt)
- Spring Validation
- Lombok
- RestClient (Spring Web)
- Maven

## Arquitectura

```
React → Backend (gateway) → API Central → NeonDB
```

El backend no accede directamente a la base de datos. Toda la obtención y modificación de datos se realiza mediante llamadas HTTP a la API Central.

## Rama: feature/estructura

Módulos incluidos:

| Módulo | Entidad | Endpoints |
|--------|---------|-----------|
| Condominio | `Condominio` | CRUD completo |
| Torre | `Torre` | CRUD completo |
| Piso | `Piso` | CRUD completo |
| Apartamento | `Apartamento` | CRUD completo |

## Requisitos

- Java 21
- Maven 3.9+
- API Central corriendo en `http://localhost:8081`

## Instalación

```bash
# 1. Clonar el repositorio
git clone -b feature/estructura <url-del-repo>

# 2. Configurar URL de la API Central (opcional, default: http://localhost:8081)
export EXTERNAL_API_BASE_URL=http://localhost:8081

# 3. Ejecutar
mvn spring-boot:run
```

## Endpoints

### Condominios (`/api/condominios`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/condominios` | Listar todos |
| GET | `/api/condominios/{id}` | Obtener por ID |
| POST | `/api/condominios` | Crear |
| PUT | `/api/condominios/{id}` | Actualizar |
| DELETE | `/api/condominios/{id}` | Eliminar |

### Torres (`/api/torres`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/torres` | Listar todas |
| GET | `/api/torres/{id}` | Obtener por ID |
| POST | `/api/torres` | Crear |
| PUT | `/api/torres/{id}` | Actualizar |
| DELETE | `/api/torres/{id}` | Eliminar |

### Pisos (`/api/pisos`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pisos` | Listar todos |
| GET | `/api/pisos/{id}` | Obtener por ID |
| POST | `/api/pisos` | Crear |
| PUT | `/api/pisos/{id}` | Actualizar |
| DELETE | `/api/pisos/{id}` | Eliminar |

### Apartamentos (`/api/apartamentos`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/apartamentos` | Listar todos |
| GET | `/api/apartamentos/{id}` | Obtener por ID |
| POST | `/api/apartamentos` | Crear |
| PUT | `/api/apartamentos/{id}` | Actualizar |
| DELETE | `/api/apartamentos/{id}` | Eliminar |

## Configuración

Toda la configuración se centraliza en `application.yml`:

```yaml
external:
  api:
    base-url: ${EXTERNAL_API_BASE_URL:http://localhost:8081}
```

Para cambiar a producción, solo modificar la variable de entorno:

```bash
export EXTERNAL_API_BASE_URL=https://api-produccion.com
```
