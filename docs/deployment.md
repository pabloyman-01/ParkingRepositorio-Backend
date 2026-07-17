# Despliegue

## Infraestructura AWS

```
CloudFront (CDN)
    ├── S3 (frontend estático)
    └── ALB → ECS Fargate (backend Spring Boot)
```

## Backend (ECS Fargate)

### Build de la imagen Docker

```bash
cd backend/
mvn clean package -DskipTests
docker build --platform linux/amd64 -t parkcontrol-backend:tag .
```

### Push a ECR

```bash
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 615161118214.dkr.ecr.us-east-1.amazonaws.com
docker tag parkcontrol-backend:tag 615161118214.dkr.ecr.us-east-1.amazonaws.com/parkcontrol-backend:tag
docker push 615161118214.dkr.ecr.us-east-1.amazonaws.com/parkcontrol-backend:tag
```

### Variables de Entorno en ECS

| Variable | Valor |
|----------|-------|
| `DB_URL` | jdbc:postgresql://host.neon.tech/db?sslmode=require |
| `DB_USER` | usuario_neon |
| `DB_PASSWORD` | password_neon |
| `EXTERNAL_API_BASE_URL` | https://parking-system-backend-0chy.onrender.com |

### Health Check

- Path: `/api/health`
- Puerto: 8080
- Grace period: 180 segundos (la app tarda ~100s en iniciar con JPA)

## Frontend (S3 + CloudFront)

### Build

```bash
cd frontend/
VITE_GATEWAY_URL=https://d21ojxpt18lomy.cloudfront.net npm run build
```

### Deploy a S3

```bash
aws s3 sync dist/ s3://parkcontrol-frontend-615161118214/ --delete
```

### Invalidar CloudFront

```bash
aws cloudfront create-invalidation --distribution-id E17MBSLHZ4D79D --paths "/*"
```

## Neon (PostgreSQL)

Las tablas se crean automáticamente via JPA `ddl-auto: update`.

Tablas gestionadas:
- `propietarios_plaza`
- `prestamos_plaza`
- `pases_invitados`

## Notas

- El ALB tiene health check timeout de 10s, intervalo 30s, umbral saludable 2.
- CloudFront tiene timeout de origen de 30s para el ALB.
- El servicio ECS tiene health check grace period de 180s.
