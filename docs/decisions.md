# Decisiones Arquitectónicas

## 1. Eliminación de la entidad Visitante

**Decisión:** Eliminar `VisitanteEntity`, `VisitanteRepository`, `VisitanteStore` y modelo `Visitante`.

**Motivo:** La entidad nunca fue poblada (0 registros en Neon). Su funcionalidad estaba cubierta por `PaseInvitadoRepository.findByPlaca()` que ya busca el nombre del invitado por matrícula. 

**Impacto:** Ninguno. No había controladores, DTOs ni frontend que dependieran de ella.

## 2. Prioridad de estadoOcupacion sobre idVehiculoActual

**Decisión:** El enriquecimiento de estacionamientos considera tanto `idVehiculoActual` como `estadoOcupacion` para determinar si una plaza está ocupada.

**Motivo:** La API Central no siempre devuelve `idVehiculoActual` después de `updateEstacionamiento`, aunque el estado sea "OCUPADO". Usar ambos campos evita falsos libres.

**Código:**
```java
boolean ocupada = e.getIdVehiculoActual() != null || "OCUPADO".equals(e.getEstadoOcupacion());
```

## 3. No modificar la API Central

**Decisión:** Toda la lógica de separación propietario/ocupante/préstamo se implementó en el BFF y en Neon, sin modificar la API Central.

**Motivo:** La API Central es un servicio externo (CondoSaaS) sobre el que no tenemos control. Modificarla rompería la compatibilidad y requeriría cambios en el proveedor.

**Impacto:** El BFF debe compensar las limitaciones de la API Central (ej: almacenar matrícula de pases localmente).

## 4. Stores locales en Neon vs en memoria

**Decisión:** Migrar de ConcurrentHashMap (en memoria) a tablas PostgreSQL en Neon.

**Motivo:** Los datos en memoria se pierden al reiniciar el contenedor ECS (especialmente en Render Free Tier). Neon persiste los datos.

**Impacto:** Startup más lento (~100s vs ~15s) por la inicialización de JPA/Hibernate.

## 5. No agregar estado EN_USO a PrestamoPlaza

**Decisión:** Mantener solo los estados ACTIVO, FINALIZADO, CANCELADO.

**Motivo:** La API Central ya gestiona las permanencias activas. Agregar EN_USO al préstamo requeriría sincronización adicional sin beneficio real. El filtro por fecha en `findByPlacaActiva()` ya controla correctamente los accesos.

## 6. Frontend llama a CloudFront (mismo dominio) vs Render directo

**Decisión:** El frontend debe usar la URL de CloudFront como gateway, no la de Render directamente.

**Motivo:** Un solo dominio evita problemas de CORS, permite caching en CloudFront, y unifica la arquitectura.

**Configuración:** `VITE_GATEWAY_URL=https://d21ojxpt18lomy.cloudfront.net` durante el build.
