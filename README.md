# ParkControl Backend

Sistema de gestión de estacionamientos. Backend que actúa como **gateway** entre el frontend y una API Central externa.

## Documentación API

- **Apidog:** [5l8mea5nhx.apidog.io](https://5l8mea5nhx.apidog.io)
- **Producción:** [https://parkingrepositorio-backend.onrender.com](https://parkingrepositorio-backend.onrender.com)

## Stack

- Java 21
- Spring Boot 3.4
- Spring Security + JWT (jjwt 0.12.6)
- Spring Validation
- Lombok
- RestClient (Spring Web)
- Maven
- Docker

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
| `feature/monitoreo` | DetallePlaza, LogAcceso, PermanenciaActiva |
| `feature/servicios` | PaseInvitado |
| `respaldo` | Backup de `dev` |

## Despliegue en Render

El proyecto incluye un `Dockerfile` para desplegar en Render.

1. Conectar repositorio de GitHub a Render
2. Crear Web Service (detecta Docker automáticamente)
3. Configurar variables de entorno:

```bash
EXTERNAL_API_BASE_URL=https://parking-system-backend-zkk6.onrender.com
JWT_SECRET=<tu-secreto-jwt>
```

## Instalación local

```bash
git clone <url-del-repo>
cd ParkingRepositorio-Backend
mvn spring-boot:run
```

## Endpoints

### Health
| Método | Ruta |
|--------|------|
| GET | `/api/health` |

### Condominios
| Método | Ruta |
|--------|------|
| GET | `/api/condominios` |
| GET | `/api/condominios/{id}` |
| POST | `/api/condominios` |
| PUT | `/api/condominios/{id}` |
| DELETE | `/api/condominios/{id}` |

### Torres
| Método | Ruta |
|--------|------|
| GET | `/api/torres` |
| GET | `/api/torres/{id}` |
| POST | `/api/torres` |
| PUT | `/api/torres/{id}` |
| DELETE | `/api/torres/{id}` |

### Pisos
| Método | Ruta |
|--------|------|
| GET | `/api/pisos` |
| GET | `/api/pisos/{id}` |
| POST | `/api/pisos` |
| PUT | `/api/pisos/{id}` |
| DELETE | `/api/pisos/{id}` |

### Apartamentos
| Método | Ruta |
|--------|------|
| GET | `/api/apartamentos` |
| GET | `/api/apartamentos/{id}` |
| POST | `/api/apartamentos` |
| PUT | `/api/apartamentos/{id}` |
| DELETE | `/api/apartamentos/{id}` |

### Roles
| Método | Ruta |
|--------|------|
| GET | `/api/roles` |
| GET | `/api/roles/{id}` |
| POST | `/api/roles` |
| PUT | `/api/roles/{id}` |
| DELETE | `/api/roles/{id}` |

### Usuarios
| Método | Ruta |
|--------|------|
| GET | `/api/usuarios` |
| GET | `/api/usuarios/{id}` |
| POST | `/api/usuarios` |
| PUT | `/api/usuarios/{id}` |
| DELETE | `/api/usuarios/{id}` |

### Vehículos
| Método | Ruta |
|--------|------|
| GET | `/api/vehiculos` |
| GET | `/api/vehiculos/{id}` |
| POST | `/api/vehiculos` |
| PUT | `/api/vehiculos/{id}` |
| DELETE | `/api/vehiculos/{id}` |

### Estacionamientos
| Método | Ruta |
|--------|------|
| GET | `/api/estacionamientos` |
| GET | `/api/estacionamientos/{id}` |
| POST | `/api/estacionamientos` |
| PUT | `/api/estacionamientos/{id}` |
| DELETE | `/api/estacionamientos/{id}` |

### Zonas Estacionamiento
| Método | Ruta |
|--------|------|
| GET | `/api/zonas-estacionamiento` |
| GET | `/api/zonas-estacionamiento/{id}` |
| POST | `/api/zonas-estacionamiento` |
| PUT | `/api/zonas-estacionamiento/{id}` |
| DELETE | `/api/zonas-estacionamiento/{id}` |

### Detalles Plaza
| Método | Ruta |
|--------|------|
| GET | `/api/detalles-plaza` |
| GET | `/api/detalles-plaza/{id}` |
| POST | `/api/detalles-plaza` |
| PUT | `/api/detalles-plaza/{id}` |
| DELETE | `/api/detalles-plaza/{id}` |

### Logs Acceso Vehicular
| Método | Ruta |
|--------|------|
| GET | `/api/logs-acceso` |
| GET | `/api/logs-acceso/{id}` |
| POST | `/api/logs-acceso` |
| PUT | `/api/logs-acceso/{id}` |
| DELETE | `/api/logs-acceso/{id}` |

### Permanencias Activas
| Método | Ruta |
|--------|------|
| GET | `/api/permanencias-activas` |
| GET | `/api/permanencias-activas/{id}` |
| POST | `/api/permanencias-activas` |
| PUT | `/api/permanencias-activas/{id}` |
| DELETE | `/api/permanencias-activas/{id}` |

### Pases Invitados
| Método | Ruta |
|--------|------|
| GET | `/api/pases-invitados` |
| GET | `/api/pases-invitados/{id}` |
| POST | `/api/pases-invitados` |
| PUT | `/api/pases-invitados/{id}` |
| DELETE | `/api/pases-invitados/{id}` |
