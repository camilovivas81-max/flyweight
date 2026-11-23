# Bitácora de uso de IA — Patrón Flyweight

Aprendices: Juan Camilo Vivas Dorado
Ficha: 3409871  Fecha de inicio: 16/11/2026

> La bitácora es evidencia del ítem 18 de la lista de chequeo. Regístralo **todo**, también las respuestas de la IA que estaban mal.
> Nunca pegues en una IA contraseñas, tokens, datos personales reales ni código que no tengas permiso de compartir.

## 1. Predicción sin IA (máx. 20 minutos)

Antes de abrir cualquier IA, lee el README y el código. Escribe aquí qué errores crees que hay y por qué.

| # | Archivo y línea | ¿Qué creo que está mal? | ¿Qué regla de negocio rompe? |
|---|---|---|---|
| 1 |FabricaTiposProducto.java (líneas 12–22) |Crea TipoProducto cuando es null (líneas 14–16), pero no hace cache.put(clave, tipo) y une textos sin separador (línea 12). |No reutiliza instancias en caché (cantidadTipos() queda en 0) y puede mezclar claves distintas. |
| 2 |ItemInventario.java (líneas 21–24) |Divide por 100 con escala 0 (RoundingMode.HALF_UP) y no valida precio nulo o negativo. |Puede redondear el IVA a entero cuando se requieren decimales o fallar con null / valores negativos. |
| 3 |Bodega.java (líneas 12–17) |Registra sin validar datos de entrada como cantidad negativa o campos vacíos (sku, ubicacion). |Permite ingresar ítems con cantidades inválidas o datos incompletos al inventario. |

## 2. Registro de interacciones con IA

Copia una fila por cada consulta importante.

| # | Herramienta (ChatGPT / Gemini / DeepSeek / Copilot / otra) | Prompt (resumen o enlace) | ¿Qué respondió? | ¿Era correcto? ¿Cómo lo verifiqué? | ¿Lo usé? (sí / no / modificado) |
|---|---|---|---|---|---|
| 1 |Gemini |Revisar las clases del ejercicio para ver qué estaba mal o roto en el código |Dijo que no se guardaban los tipos creados para reutilizarlos, que juntaba palabras sin separador y que se podía cambiar el IVA después de crearlo |Sí en lo principal; lo comprobé mirando el código y probando qué pasaba al registrar dos productos iguales |modificado |
| 2 |ChatGPT |Cómo hacer pruebas para comprobar si dos productos usan el mismo tipo y si el cálculo del IVA da bien |Me dio ejemplos de pruebas para revisar si el tipo era el mismo y comparar el resultado del IVA con un número esperado |Sí; lo comprobé corriendo las pruebas y viendo que fallaban antes de arreglar el código |Si |
| 3 |Copilot |Qué revisar si llega un precio vacío o negativo al calcular el IVA |Sugirió lanzar un error si el precio venía vacío o menor que cero |Más o menos; la idea de revisar el precio vacío sirvió, pero preferí ajustarlo a lo simple que pedía el ejercicio |modificado |

## 3. Jurado de IAs

Envía el **mismo prompt** a tres herramientas distintas y compara.

| Pregunta enviada | ChatGPT | Gemini | DeepSeek | ¿Quién acertó? ¿En qué discreparon? |
|---|---|---|---|---|
|¿Qué está mal al armar la clave y guardar los tipos de producto en este código? |Dijo que faltaba guardar el tipo creado y que unir textos sin separador puede mezclar datos; propuso poner un separador como "|" o usar algo más ordenado |Dijo que nunca se guarda en la lista/mapa (por eso siempre da 0) y que pegar textos directo puede generar choques |Dijo que faltaba guardar el nuevo tipo y propuso separar o acomodar los textos para que no se confundan |Los tres acertaron en que faltaba guardar y que pegar textos sin separador podía dar problemas. Solo cambiaron en cómo acomodar o separar esa clave |

## 4. Errores encontrados y corregidos

| # | Error | Prueba que lo demuestra (nombre del método de prueba) | Commit de la corrección | ¿Lo encontré yo, la IA o ambos? |
|---|---|---|---|---|
| 1 |FabricaTiposProducto.obtener(...) creaba TipoProducto pero no llamaba a cache.put(clave, tipo) y concatenaba clave sin separador, impidiendo reutilizar instancias |debeReutilizarMismaInstanciaTipoProducto() |fix(flyweight): guardar instancias en cache y separar clave |ambos |
| 2 |ItemInventario.valorIva(...) redondeaba a escala 0 (divide(..., 0, RoundingMode.HALF_UP)), perdiendo decimales en el cálculo del IVA y sin validar entradas nulas o negativas |debeCalcularIvaConDosDecimalesSinPerderPrecision() |fix(inventario): calcular IVA con dos decimales y validar precio |IA |
| 3 |Bodega.registrar(...) permitía registrar ítems con cantidad negativa o campos obligatorios vacíos/nulos sin validar |debeRechazarRegistroConCantidadNegativaOSkuVacio() |fix(bodega): validar datos de entrada al registrar item |ambos |

## 5. Lo que la IA hizo mal o no vio

Describe por lo menos un caso en que la IA se equivocó, inventó algo o propuso un cambio peligroso. ¿Cómo te diste cuenta?
Error / respuesta incorrecta de la IA: Al pedir el código original y ajustar el porcentaje de IVA, la IA devolvió una versión reescrita donde mezcló responsabilidades (poniendo atributos y lógica de TipoProducto dentro de una clase llamada FabricaTipoProductos con un constructor TipoProducto(...) que no coincide con el nombre de la clase), además de asumir detalles del código original que no estaban en el archivo.

Cómo me di cuenta: Al revisar el código y los nombres de clase/constructor en Java noté la inconsistencia de nombres y la mezcla de clases, por lo que separé nuevamente FabricaTiposProducto, ItemInventario y Bodega y dejé solo las correcciones reales que necesitaba el ejercicio.

## 6. Reflexión final (5 a 10 líneas)

¿Qué parte del trabajo aceleró la IA? ¿En qué momento decidiste **no** usarla y por qué? ¿Qué aprendiste del patrón que no sabías antes?

La IA aceleró principalmente la identificación rápida de fallos puntuales —como olvidar cache.put en la fábrica o revisar la escala del redondeo en valorIva— y me ayudó a redactar y estructurar pruebas unitarias en JUnit 5 más rápido. Decidí no usarla (o descartar sus sugerencias) cuando empezó a proponer cambios más grandes o validaciones extra que complicaban el ejercicio sin aportar al objetivo principal, o cuando devolvió código con nombres mezclados que no correspondían a la estructura real del proyecto. Del patrón Flyweight aprendí que su valor real está en separar claramente el estado intrínseco (compartido e inmutable a través de una fábrica con caché) del estado extrínseco (propio de cada ítem), y en asegurar que la clave del caché identifique bien cada tipo sin colisiones.