# MercaSENA · Patrón Flyweight (Estructural)

**Dominio:** Inventario masivo con tipos de producto compartidos

## Contexto

La bodega de MercaSENA registra **cientos de miles** de unidades. Cada ítem tiene datos propios (SKU, cantidad, ubicación), pero muchos comparten los mismos datos de **tipo**: categoría, unidad de medida y porcentaje de IVA. Repetir esos datos en cada ítem consume mucha memoria. Con **Flyweight**, los datos compartidos (estado **intrínseco**) se guardan una sola vez en `TipoProducto` y cada ítem guarda solo sus datos propios (estado **extrínseco**).

Clases principales:

- `TipoProducto`: el *flyweight*. Es inmutable.
- `FabricaTiposProducto`: entrega y reutiliza los *flyweights*.
- `ItemInventario`: guarda el estado extrínseco y una referencia al tipo.
- `Bodega`: cliente.

## Reglas de negocio

1. Pedir dos veces un tipo con la misma categoría, la misma unidad y el mismo IVA devuelve **exactamente el mismo objeto** (`==`).
2. Dos tipos que difieren en **cualquiera** de sus tres datos son objetos distintos. Ojo con combinaciones como (`"Aseo"`, `"und"`, 19) y (`"Aseo"`, `"und1"`, 9): son tipos diferentes.
3. **`TipoProducto` es inmutable:** sus atributos son `final` y no tiene *setters*. Como es compartido por miles de ítems, cambiarlo afectaría a todos.
4. `valorIva(precio)` de un ítem es `precio × IVA / 100`, redondeado a pesos enteros (*half up*).

## Tu misión

Este proyecto implementa el patrón **Flyweight** dentro de la tienda en línea **MercaSENA**. El código *compila*, pero tiene **errores sembrados**: algunos los detectan las pruebas que ya existen y otros **no**. Tu trabajo es encontrarlos todos, demostrarlos con pruebas y corregirlos con ayuda de la IA, sin perder el control técnico.

> La fuente de verdad son las **reglas de negocio** de este README. La IA no las conoce si no se las das.

## Protocolo de trabajo (obligatorio)

1. **Prepara el repositorio.** Crea un repositorio en GitHub con este proyecto y haz el primer commit sin cambios: `git commit -m "chore: estado inicial"`.
2. **Predice sin IA (20 min).** Lee este README y el código. Escribe tus hipótesis en `BITACORA_IA.md`, sección 1.
3. **Ejecuta las pruebas.** Corre `mvn test` o las pruebas desde IntelliJ / Eclipse. Anota cuáles fallan.
4. **Demuestra cada error con una prueba.** Por cada error que encuentres, incluidos los que las pruebas actuales no detectan, escribe primero una prueba que **falle**. Haz commit: `test: demuestra <error>`.
5. **Consulta al jurado de IAs.** Envía el mismo prompt a ChatGPT, Gemini y DeepSeek. Usa el prompt de 5 piezas: contexto, tarea, restricciones, formato y criterio de verificación. Registra en la bitácora en qué coincidieron y en qué no.
6. **Corrige.** Aplica la corrección que **tú** entiendes y puedes explicar. La prueba debe pasar. Haz commit: `fix: corrige <error>`.
7. **Refactoriza con IA.** Pide a la IA una mejora de legibilidad que no cambie el comportamiento. Todas las pruebas deben seguir en verde. Haz commit: `refactor: ...`.
8. **Revisa la seguridad.** Pregúntate qué podría hacer un usuario malicioso con este código y anota tu respuesta en la bitácora.
9. **Prepara la defensa.** El instructor te pedirá explicar, **sin IA**, cualquier línea que hayas cambiado.

## Cómo ejecutar

```bash
mvn test
```

Requisitos: JDK 17 o superior y Maven 3.9 o superior, o el Maven que trae IntelliJ IDEA o Eclipse.

## Qué NO hacer

- No borres ni debilites pruebas para que pasen.
- No pegues el proyecto completo en una IA sin explicarle las reglas de negocio.
- No aceptes una corrección que no sabes explicar.

## Referencia del patrón

- https://refactoring.guru/es/design-patterns/flyweight
