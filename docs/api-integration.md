# Integración con API Central

## Descripción

El BFF se comunica con una API Central externa (CondoSaaS) alojada en Render. Esta API es la fuente de verdad para:

- Estacionamientos (plazas, estado, vehículo actual)
- Vehículos registrados
- Permanencias (entradas/salidas)
- Pases de invitado (parcialmente)
- Usuarios, condominios, torres, pisos, apartamentos

## Autenticación

La API Central usa JWT. El BFF se autentica en el inicio:

```
POST /api/auth/login
Body: { "email": "admin@condosaas.com", "password": "Admin123" }
Response: { "token": "eyJ..." }
```

El `AuthInterceptor` agrega el token a todas las peticiones salientes.

Si la API Central responde con 401, el interceptor re-autentica automáticamente.

## Endpoints Utilizados

| Método | Endpoint | Propósito |
|--------|----------|-----------|
| GET | /api/estacionamiento | Listar todas las plazas |
| PUT | /api/estacionamiento/{id}/update | Actualizar estado de plaza |
| GET | /api/vehiculos | Listar vehículos |
| POST | /api/permanencias/registrar-entrada | Registrar entrada |
| POST | /api/permanencias/registrar-salida | Registrar salida |
| GET | /api/permanencias | Listar permanencias |
| GET | /api/usuarios | Listar usuarios |
| GET | /api/condominios | Listar condominios |
| GET | /api/pases-invitado | Listar pases de invitado |
| POST | /api/pases-invitado/create | Crear pase de invitado |

## Datos Complementarios (Neon)

La API Central NO gestiona los siguientes datos. Se almacenan localmente en Neon:

- **PropietarioPlaza**: Asignación de propietario permanente a una plaza
- **PrestamoPlaza**: Préstamos temporales entre propietarios
- **PaseInvitado (matrícula)**: La API Central no devuelve la matrícula del vehículo al crear pases

## Limitaciones Conocidas

1. **idVehiculoActual**: La API Central no siempre devuelve `idVehiculoActual` tras `updateEstacionamiento`. El BFF usa `estadoOcupacion` como fuente complementaria.
2. **Sleep de Render**: El plan Free Tier de Render duerme el servicio tras inactividad. La primera solicitud puede tardar 5-30s.
3. **Matrícula en pases**: La API Central no devuelve `matricula` ni `idApartamento` al crear pases de invitado. El BFF almacena estos datos localmente.
