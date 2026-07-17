# Seguridad

## Estado Actual

El proyecto NO implementa autenticación de usuarios para el frontend. Todos los endpoints `/api/*` son públicos (`permitAll()`). Esto es intencional: el sistema está diseñado como una herramienta administrativa interna que se ejecuta en una red controlada (CloudFront + ALB).

La infraestructura AWS (CloudFront, ALB, VPC) proporciona la primera capa de seguridad.

## Endpoints Públicos

| Endpoint | Método | Propósito |
|----------|--------|-----------|
| `/api/health` | GET | Health check del ALB |
| `/api/*` | GET/POST/PUT/DELETE | Todos los endpoints del BFF |

## JWT (Sistema Interno)

El JWT se utiliza para la comunicación entre el BFF y la API Central (CondoSaaS). No se utiliza para proteger los endpoints del BFF.

- **Generación**: `JwtTokenProvider` mediante HMAC-SHA256
- **Secreto**: Configurable via `JWT_SECRET` (variable de entorno)
- **Expiración**: 8h (token de acceso), 7d (refresh)
- **Validación**: `JwtAuthFilter` extrae el token Bearer de los requests entrantes
- Si el frontend necesita autenticación en el futuro, ya está implementado el mecanismo

## Medidas de Seguridad Implementadas

### Backend
1. **CSRF deshabilitado**: Apropiado para API REST stateless
2. **Sesiones STATELESS**: Sin sesiones HTTP
3. **CORS limitado**: Acepta cualquier origen (necesario para frontend en CloudFront)
4. **Credenciales de API Central**: Configurables via `AUTH_EMAIL` / `AUTH_PASSWORD`
5. **JWT Secret**: Configurable via variable de entorno `JWT_SECRET`
6. **Timeout de conexión**: 10s para la API Central
7. **Pool de BD**: Máximo 5 conexiones Hikari

### Infraestructura AWS
1. **CloudFront**: CDN con HTTPS, protección DDoS
2. **ALB**: Terminación TLS, health checks
3. **ECS Fargate**: Contenedores aislados, sin SSH
4. **Security Groups**: Solo tráfico del ALB al ECS

## Recomendaciones para Producción

1. **Activar autenticación**: Implementar login de usuarios y cambiar `permitAll()` por `authenticated()`
2. **ddl-auto: validate**: Cambiar a `validate` con migraciones manuales (Flyway/Liquibase)
3. **Rate Limiting**: Agregar límite de requests por IP
4. **WAF**: AWS WAF delante de CloudFront
5. **Secrets Manager**: Usar AWS Secrets Manager para las credenciales de Neon y API Central
