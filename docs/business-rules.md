# Reglas de Negocio

## Conceptos Fundamentales

| Concepto | Definición |
|----------|------------|
| **Propietario** | Dueño permanente de la plaza. Solo cambia por proceso administrativo. |
| **Ocupante** | Quién está usando la plaza actualmente. Puede ser el propietario o un autorizado. |
| **Préstamo** | Autorización temporal del propietario a un tercero. |
| **Pase Temporal** | Autorización para visitantes con fecha de inicio/fin. |

## Reglas de Propietario

1. Cada plaza SIEMPRE tiene un propietario asignado (o está disponible para asignación).
2. El propietario **nunca** se elimina por el flujo diario (entrada/salida).
3. Asignar un propietario **no** marca la plaza como ocupada.
4. El propietario tiene **prioridad absoluta** sobre su plaza.
5. Si el propietario está ocupando la plaza, un usuario con préstamo **no puede** ingresar.
6. Mensaje al autorizado: *"La plaza se encuentra actualmente ocupada por el propietario."*

## Reglas de Ocupación

1. `idVehiculoActual` (API Central) + `estadoOcupacion` determinan si una plaza está ocupada.
2. `tipoUso` puede ser: `PROPIO`, `PRESTAMO`, `VISITANTE` (nunca mezclado con estado).
3. `prestamoExpirado` (boolean) indica si el préstamo venció, independiente de `tipoUso`.
4. Cuando el ocupante sale, la plaza vuelve a **disponible**. El propietario no cambia.

## Reglas de Préstamo

1. Solo un propietario puede prestar su plaza.
2. No pueden existir préstamos superpuestos para la misma plaza (validación en backend).
3. El préstamo tiene fecha/hora de inicio y fin.
4. El autorizado solo puede ingresar dentro del periodo `[fechaInicio, fechaFin]`.
5. Si el préstamo expira mientras el autorizado está dentro, **no se expulsa**.
6. Al salir, si el préstamo sigue vigente, el autorizado puede **volver a entrar**.
7. Al cambiar de propietario, los préstamos activos se **cancelan automáticamente**.

## Reglas de Pase Temporal

1. El pase tiene código único, placa, nombre del invitado, fecha inicio/fin.
2. El invitado solo puede ingresar durante el periodo activo.
3. `tipoOcupante` enviado a API Central: `"INQUILINO_TEMPORAL"`.

## Reglas de Control de Acceso

| Tipo | ¿Puede ingresar? | Condición |
|------|-------------------|-----------|
| PROPIETARIO | ✅ | Si hay espacio libre en su condominio |
| PRESTAMO | ✅ | Si la plaza NO está ocupada por el propietario |
| VISITANTE (vehículo) | ✅ | Siempre (si está registrado como VISITANTE) |
| INQUILINO_TEMPORAL | ✅ | Siempre (si el pase está activo) |
| DESCONOCIDO | ❌ | Rechazado |

## Estados

### PrestamoPlaza
- `ACTIVO`: Vigente, aún no utilizado o el autorizado ya salió
- `FINALIZADO`: Terminado manualmente
- `CANCELADO`: Cancelado (ej: al cambiar propietario)

### PermanenciaActiva
- `ACTIVA`: El vehículo está dentro
- `FINALIZADA`: El vehículo salió

### Estacionamiento (vía API Central)
- `LIBRE`: Disponible
- `OCUPADO`: En uso
- `INACTIVO`: En mantenimiento
