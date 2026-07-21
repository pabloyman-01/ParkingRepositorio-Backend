# Arquitectura del Sistema

## Visión General

```
Usuario (Browser)
    ↓
CloudFront (d21ojxpt18lomy.cloudfront.net)
    ├── /*        → S3 (React SPA)
    └── /api/*    → ALB → ECS Fargate (Spring Boot BFF)
                                ↓
                    ┌───────────────────────┐
                    ↓                       ↓
            API Central (Render)       Neon (PostgreSQL)
            (CondoSaaS externo)        (Datos complementarios)
```

## Capas del Backend

### Controller Layer
- 16 controladores REST
- Endpoints públicos (`permitAll()`)
- Respuestas envueltas en `ApiResponse<T>`
- Validación via `@Valid`

### Service Layer
- Lógica de negocio
- Orquestación de providers + stores
- `EstacionamientoService`: enriquecimiento de plazas con datos de propietarios/ocupantes

### Provider Layer
- Abstracción sobre los clientes HTTP
- 1 provider por cada API Central endpoint

### Client Layer
- Comunicación HTTP con API Central via `RestClient`
- `AuthInterceptor`: agrega token JWT Bearer automáticamente
- `MappingUtil`: conversión DTO → Model

### Store Layer
- Almacenamiento local en Neon (JPA)
- PropietarioPlazaStore, PrestamoPlazaStore, PaseInvitadoStore
- Datos que la API Central no gestiona

## Capas del Frontend

### Context Layer
- `ParkingContext`: estado global via React Context
- 5 slices de estado: vehicles, parkingSpaces, accessLog, propietariosPlaza, prestamosPlaza
- Acciones: CRUD vehículos, entrada/salida, asignar owner, crear préstamo

### Service Layer
- `parkingService`: wrapper de fetch() con normalización de respuestas
- Base URL configurable via `VITE_GATEWAY_URL`

### Component Layer
- Páginas (Dashboard, Control de Acceso, Mapa, etc.)
- Componentes compartidos (modales, tablas, grids)
- Hooks (useParkingSelection)

## Flujo de Datos

### Carga Inicial
```
1. ParkingProvider monta → loadAll()
2. 5 llamadas paralelas: vehiculos, estacionamientos, permanencias, propietarios, prestamos
3. Los datos se normalizan y almacenan en contexto
4. Las páginas consumen el contexto
```

### Registro de Entrada
```
1. VehicleEntry detecta tipo de ocupante (PROPIETARIO/PRESTAMO/VISITANTE/DESCONOCIDO)
2. grantAccess() → registrarEntrada(placa, tipoOcupante)
3. updateEstacionamiento(id, OCUPADO, idVehiculoActual)
4. loadAll() → refresca todo el estado
```

### Registro de Salida
```
1. registerExit(placa) → registrarSalida(placa, tipoOcupante)
2. updateEstacionamiento(id, LIBRE)
3. loadAll()
```
