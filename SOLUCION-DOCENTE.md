# Solución docente del integrador

Esta rama resuelve la consigna obligatoria de stock. `main` conserva el proyecto base. No se agregaron persistencia en TXT, búsqueda por ID ni validación de longitud del nombre: eran propuestas posteriores u opcionales.

## Qué cambió
- `Producto.java`: atributo `Integer stock`, parámetro del constructor, getter y setter. `Integer` permite detectar el dato ausente como `null`; cero sigue siendo válido.
- `ProductoController.java`: stock de los dos ejemplos iniciales (10 y 0); rechazo con 400 cuando el stock es nulo o negativo, antes de agregar a la lista.
- `index.html`: campo Stock y encabezado de la columna. Se conserva `novalidate` para poder probar los errores del backend.
- `app.js`: lee el nuevo campo, envía `null` si está vacío y agrega el stock a cada fila de la tabla.
- `application.properties`: rechaza la conversión de decimales a enteros. Así un stock 1.5 no se guarda como 1.

## Ejecutar esta versión
En esta rama, usar los mismos comandos del tutorial: `./mvnw spring-boot:run` en Mac/Linux o `.\mvnw.cmd spring-boot:run` en Windows. Reiniciar la ejecución anterior para cargar el código modificado.

## Pruebas para demostrar
1. GET `/api/productos`: los ejemplos incluyen stock 10 y 0.
2. POST válido: `{"nombre":"Carpeta","precio":1500,"stock":5}` devuelve 201.
3. Repetir con stock 0: devuelve 201.
4. Stock -1, null o propiedad stock omitida: devuelve 400.
5. Stock 1.5: devuelve 400 al convertir el JSON, antes de entrar al método del controller.
6. Nombre vacío o precio cero/negativo con stock válido: sigue devolviendo 400.
7. Consultar con GET: sólo los POST válidos aumentan la lista; todos muestran stock.

Para Postman: Body → raw → JSON. Los POST válidos del anexo original necesitan ahora agregar `"stock":5`. No enviar ID: lo asigna el servidor.

## Explicación para la exposición
Formulario → app.js → JSON → @RequestBody → validaciones → productos.add(producto) → respuesta 201 → JSON. GET recupera la lista y JavaScript muestra el stock en la tabla.

El dato permanece en memoria: reiniciar restaura los dos ejemplos iniciales.
