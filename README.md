# ParkControl Backend

Sistema de gestión de estacionamientos. Backend que actúa como **gateway** entre el frontend y una API Central externa.

## Documentación API

- **Apidog:** [sizfi8yxf5.apidog.io](https://sizfi8yxf5.apidog.io)

## Stack

- Java 21
- Spring Boot 3.4
- Spring Security + JWT (jjwt 0.12.6)
- Spring Validation
- Lombok
- RestClient (Spring Web)
- Maven

## Arquitectura

```
React → Backend (gateway) → API Central → NeonDB
```

El backend no accede directamente a la base de datos. Toda la obtención y modificación de datos se realiza mediante llamadas HTTP a la API Central a través de la capa de proveedores API.

### Capas del proyecto

```
Controller → Service → ApiProvider → ApiClient → HTTP → API Central
```

- **Controller**: Define los endpoints REST que consume React.
- **Service**: Lógica de negocio y orquestación.
- **ApiProvider**: Implementación del proveedor de datos vía API.
- **ApiClient**: Cliente HTTP (RestClient) que realiza las llamadas a la API Central.

## Ramas

| Rama | Módulos |
|------|---------|
| `dev` | Rama principal de integración |
| `base` | Infraestructura compartida (modelos, config, seguridad) |
| `feature/estructura` | Condominio, Torre, Piso, Apartamento |
| `feature/usuarios` | Rol, Usuario |
| `feature/vehiculos` | Vehículo, Estacionamiento, ZonaEstacionamiento |
| `feature/monitoreo` | DetallePlaza, DetalleAcceso, LogAcceso, PermanenciaActiva |
| `feature/servicios` | PaseInvitado |

## Requisitos

- Java 21
- Maven 3.9+
- API Central corriendo en `http://localhost:8081` (o la URL configurada)

## Instalación

```bash
# 1. Clonar el repositorio
git clone <url-del-repo>

# 2. Configurar URL de la API Central (opcional)
export EXTERNAL_API_BASE_URL=http://localhost:8081

# 3. Ejecutar
mvn spring-boot:run
```

## Endpoints

### Health
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/health` | Health check del servicio |

### Condominios
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/condominios` | Listar todos |
| GET | `/api/condominios/{id}` | Obtener por ID |
| POST | `/api/condominios` | Crear |
| PUT | `/api/condominios/{id}` | Actualizar |
| DELETE | `/api/condominios/{id}` | Eliminar |

### Torres
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/torres` | Listar todas |
| GET | `/api/torres/{id}` | Obtener por ID |
| POST | `/api/torres` | Crear |
| PUT | `/api/torres/{id}` | Actualizar |
| DELETE | `/api/torres/{id}` | Eliminar |

### Pisos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pisos` | Listar todos |
| GET | `/api/pisos/{id}` | Obtener por ID |
| POST | `/api/pisos` | Crear |
| PUT | `/api/pisos/{id}` | Actualizar |
| DELETE | `/api/pisos/{id}` | Eliminar |

### Apartamentos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/apartamentos` | Listar todos |
| GET | `/api/apartamentos/{id}` | Obtener por ID |
| POST | `/api/apartamentos` | Crear |
| PUT | `/api/apartamentos/{id}` | Actualizar |
| DELETE | `/api/apartamentos/{id}` | Eliminar |

### Roles
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/roles` | Listar todos |
| GET | `/api/roles/{id}` | Obtener por ID |
| POST | `/api/roles` | Crear |
| PUT | `/api/roles/{id}` | Actualizar |
| DELETE | `/api/roles/{id}` | Eliminar |

### Usuarios
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/usuarios` | Listar todos |
| GET | `/api/usuarios/{id}` | Obtener por ID |
| POST | `/api/usuarios` | Crear |
| PUT | `/api/usuarios/{id}` | Actualizar |
| DELETE | `/api/usuarios/{id}` | Eliminar |

### Vehículos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/vehiculos` | Listar todos |
| GET | `/api/vehiculos/{id}` | Obtener por ID |
| POST | `/api/vehiculos` | Crear |
| PUT | `/api/vehiculos/{id}` | Actualizar |
| DELETE | `/api/vehiculos/{id}` | Eliminar |

### Estacionamientos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/estacionamientos` | Listar todos |
| GET | `/api/estacionamientos/{id}` | Obtener por ID |
| POST | `/api/estacionamientos` | Crear |
| PUT | `/api/estacionamientos/{id}` | Actualizar |
| DELETE | `/api/estacionamientos/{id}` | Eliminar |

### Zonas Estacionamiento
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/zonas-estacionamiento` | Listar todas |
| GET | `/api/zonas-estacionamiento/{id}` | Obtener por ID |
| POST | `/api/zonas-estacionamiento` | Crear |
| PUT | `/api/zonas-estacionamiento/{id}` | Actualizar |
| DELETE | `/api/zonas-estacionamiento/{id}` | Eliminar |

### Detalles Plaza
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/detalles-plaza` | Listar todos |
| GET | `/api/detalles-plaza/{id}` | Obtener por ID |
| POST | `/api/detalles-plaza` | Crear |
| PUT | `/api/detalles-plaza/{id}` | Actualizar |
| DELETE | `/api/detalles-plaza/{id}` | Eliminar |

### Detalles Acceso
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/detalles-acceso` | Listar todos |
| GET | `/api/detalles-acceso/{id}` | Obtener por ID |
| POST | `/api/detalles-acceso` | Crear |
| PUT | `/api/detalles-acceso/{id}` | Actualizar |
| DELETE | `/api/detalles-acceso/{id}` | Eliminar |

### Logs Acceso Vehicular
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/logs-acceso` | Listar todos |
| GET | `/api/logs-acceso/{id}` | Obtener por ID |
| POST | `/api/logs-acceso` | Crear |
| PUT | `/api/logs-acceso/{id}` | Actualizar |
| DELETE | `/api/logs-acceso/{id}` | Eliminar |

### Permanencias Activas
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/permanencias-activas` | Listar todas |
| GET | `/api/permanencias-activas/{id}` | Obtener por ID |
| POST | `/api/permanencias-activas` | Crear |
| PUT | `/api/permanencias-activas/{id}` | Actualizar |
| DELETE | `/api/permanencias-activas/{id}` | Eliminar |

### Pases Invitados
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/pases-invitados` | Listar todos |
| GET | `/api/pases-invitados/{id}` | Obtener por ID |
| POST | `/api/pases-invitados` | Crear |
| PUT | `/api/pases-invitados/{id}` | Actualizar |
| DELETE | `/api/pases-invitados/{id}` | Eliminar |

## Configuración

Toda la configuración se centraliza en `application.yml`:

```yaml
external:
  api:
    base-url: ${EXTERNAL_API_BASE_URL:http://localhost:8081}
```

Para cambiar de entorno, solo modificar la variable de entorno:

```bash
# Desarrollo
export EXTERNAL_API_BASE_URL=http://localhost:8081

# Producción
export EXTERNAL_API_BASE_URL=https://api-produccion.com
```
