# Laboratorio II — Integrador: API de productos

Este trabajo evaluado comienza en una clase, continúa en la siguiente y finaliza con una exposición en grupos de 2 o 3 integrantes. Avancen por etapas: no es obligatorio terminar una etapa en un día determinado. Los contenidos forman parte de la preparación para el examen.

## Objetivo
Ejecutar una aplicación, comprender cómo viajan los datos entre una página y Java, modificarla y explicar el resultado. El proyecto base ya funciona. La modificación evaluada NO está resuelta.

## Si tienen dudas
Consulten a la docente. También pueden usar documentación o un chat como apoyo, pero deben probar lo que les sugiera y poder explicarlo con sus palabras. Si algo falla, muestren qué estaban haciendo y el mensaje que apareció.

## Para empezar, hagan solamente esto
1. Abran el proyecto en VS Code.
2. Sigan `COMO-EJECUTAR.txt` para iniciarlo.
3. Abran la página y prueben Consultar productos y Enviar producto.
4. Después continúen con la lectura del código y la modificación solicitada.

## 1. Obtener el proyecto con Git
La docente compartirá la URL del repositorio. En VS Code, usen **Git: Clone** desde la paleta de comandos y peguen esa URL, o ejecuten `git clone URL_DEL_REPOSITORIO` reemplazando el texto por la dirección real. Abran la carpeta que contiene `pom.xml`.

Git registra versiones localmente. GitHub aloja repositorios y permite compartirlos. Un commit guarda una versión local; un push la envía al remoto. Clonar no les da permiso de escritura sobre el repositorio de la docente. Para entregar, usen un fork del grupo si está habilitado, o un repositorio propio siguiendo las indicaciones de clase. Todos deben poder explicar sus aportes.

**Comprobación:** encuentren `pom.xml`, `src` y `mvnw.cmd`.

## 2. Preparar y ejecutar

Si necesitás los pasos desde cero, abrí [COMO-EJECUTAR.txt](COMO-EJECUTAR.txt): incluye comandos, mensajes esperados y cómo detener y reiniciar.
Necesitan **JDK 21**, Git, VS Code y la extensión **Extension Pack for Java**. JDK incluye las herramientas para compilar y ejecutar Java. Comprueben en una terminal nueva:

```sh
java -version
javac -version
```

Ambos deben indicar versión 21. Si no aparecen, revisen la instalación o la configuración de PATH/JAVA_HOME con la docente. Tener una extensión no garantiza que el JDK del proyecto esté configurado.

Desde la carpeta que contiene `pom.xml`:

**Windows, PowerShell:**
```powershell
.\mvnw.cmd spring-boot:run
```

**macOS o Linux:**
```sh
./mvnw spring-boot:run
```

La primera vez se necesita internet y puede demorar: se descargan Maven y las dependencias. Esperen el mensaje de inicio y abran **http://localhost:8080/**. Mantengan abierta la terminal. Para detener: Ctrl+C. Después de cambiar Java, detengan y vuelvan a iniciar. Después de cambiar HTML/JavaScript, reinicien y recarguen el navegador para asegurarse de ver la nueva versión.

`localhost` significa esta computadora. `8080` es el puerto del servidor. Cada grupo ejecuta su propia aplicación. No abran el HTML con doble clic ni con Live Server: usen la dirección del servidor Java.

**Comprobación:** aparece la página y GET muestra Cuaderno y Lapicera.

## 3. Investigar Maven y pom.xml
No necesitan instalar Spring Boot por separado. `pom.xml` describe el proyecto, la versión de Java y sus dependencias. Maven utiliza ese archivo para descargar bibliotecas y compilar, probar y ejecutar el proyecto. `mvnw`, `mvnw.cmd` y `.mvn/` forman Maven Wrapper: permiten usar la versión prevista de Maven sin instalarla manualmente. No los borren y súbanlos a GitHub.

Busquen en `pom.xml`:
- ¿Qué versión de Java declara?
- ¿Qué versión de Spring Boot usa el proyecto?
- ¿Qué dependencia permite construir la aplicación web?
- ¿Qué diferencia hay entre una dependencia y el plugin `spring-boot-maven-plugin`?
- ¿Por qué una dependencia de Maven no es lo mismo que una extensión de VS Code?

Consulten la documentación y expliquen con sus palabras; incluyan enlaces en el informe.

## 4. Reconocer la estructura

```text
pom.xml                           Configuración de Maven y dependencias
mvnw / mvnw.cmd / .mvn/            Maven Wrapper
src/main/java/ar/edu/laboratorio/productos/
  ProductoApplication.java        Punto de entrada: inicia Spring Boot
  controller/ProductoController.java  Recibe GET y POST
  model/Producto.java             Datos, constructores, getters y setters
src/main/resources/
  application.properties          Nombre de la aplicación y puerto
  static/index.html               Formulario y tabla
  static/estilos.css              Estilos básicos de la página
  static/app.js                   Envía peticiones y muestra respuestas
```

El navegador ejecuta HTML/JavaScript; Java corre en el servidor. Spring Boot inicia un servidor web integrado. En este ejemplo el controller también administra una lista en memoria: no hay base de datos. Es una simplificación para estudiar HTTP; una aplicación más grande suele separar responsabilidades en más capas.

## 5. Probar y seguir el recorrido
1. Pulsen **Consultar productos**: `GET /api/productos`, respuesta `200` y una lista JSON. Este GET no lleva cuerpo.
2. Agreguen un producto con nombre y precio positivo: `POST /api/productos`, cuerpo JSON y respuesta `201` con el producto creado. El servidor asigna el ID.
3. Vuelvan a consultar con GET para comprobar que se guardó.
4. Envíen nombre vacío: respuesta `400` y explicación del error.
5. Envíen precio cero o negativo: respuesta `400`; el producto no debe agregarse.
6. Reinicien la aplicación y consulten: expliquen por qué desaparecen los productos agregados.

JSON es un formato de texto para intercambiar datos; no es una base de datos. Un POST envía, por ejemplo:

```json
{"nombre": "Carpeta", "precio": 1500}
```

El encabezado `Content-Type: application/json` indica el formato del cuerpo. `JSON.stringify` crea ese texto en el navegador; `@RequestBody` permite que Spring lo convierta en un `Producto`. El controller construye una respuesta y Spring serializa los datos a JSON. El navegador recibe la respuesta y actualiza la pantalla.

Abran las herramientas de desarrollador del navegador → **Red/Network**, ejecuten una petición y busquen método, URL, Payload/cuerpo, estado y Response/respuesta. No necesitan Postman. Si prefieren usarlo, al final de [COMO-EJECUTAR.txt](COMO-EJECUTAR.txt) hay un anexo opcional con pasos y comandos cURL listos para importar.

**Para explicar:** ¿qué diferencia hay entre el objeto Java, el texto JSON y el objeto JavaScript? ¿Qué método atiende cada petición? ¿Por qué un POST puede devolver 201 o 400?

## Lectura guiada antes de modificar
No se espera que ya conozcan todas las instrucciones. Lean, ejecuten, consulten fuentes y expliquen con sus palabras:

1. ¿Qué inicia `SpringApplication.run(...)`? ¿Por qué debe seguir ejecutándose para que funcione la página?
2. ¿Qué representa `Producto` y para qué sirven sus getters y setters?
3. ¿Qué guardan `List<Producto>` y `new ArrayList<>()`? En el GET, `new ArrayList<>(productos)` copia la lista: no la convierte a JSON. ¿Quién hace esa conversión?
4. ¿Cuándo se ejecuta el constructor `ProductoController()` y por qué carga dos productos? ¿Se vuelve a ejecutar por cada GET?
5. ¿Qué indican `@RestController`, `@RequestMapping`, `@GetMapping` y `@PostMapping`? Señalen la URL y el método HTTP de cada operación.
6. ¿Qué hace `@RequestBody` con el JSON recibido?
7. ¿Qué verifican `null`, `isBlank()` y la comparación del precio? Prueben un nombre compuesto solo por espacios.
8. ¿Qué representan `ResponseEntity`, `badRequest()`, `status(201)` y `body()`? `Map.of("error", mensaje)` construye los datos del mensaje de error que se devolverá como JSON. ¿Por qué `body()` no significa siempre éxito?
9. ¿Qué diferencias hay entre asignar con `setId(...)` y agregar con `productos.add(...)`? ¿Qué hace `siguienteId++`?
10. ¿Qué declara `pom.xml` y qué descarga Maven? Distingan dependencias, plugins de Maven y extensiones de VS Code.

Ayuda: `ResponseEntity<?>` permite que la respuesta contenga un producto o un mensaje de error. `synchronized` evita que dos peticiones ejecuten estos métodos al mismo tiempo sobre la lista compartida; no es un contenido que deban desarrollar en este integrador. El objetivo es explicar el recorrido, no memorizar sintaxis nueva.

**Comprobación:** cada integrante puede señalar dónde entra una petición, dónde se validan los datos y dónde se arma la respuesta. No se pide escribir tests automatizados: realicen las pruebas manuales del tutorial.

## 6. Modificación obligatoria del grupo: agregar stock
Agreguen el dato **stock**, que representa la cantidad disponible. Debe ser un número entero mayor o igual que cero y ser obligatorio. No se acepta resolverlo solamente en la pantalla.

- Incorporen el dato al modelo Java y a los productos iniciales.
- Agreguen el campo al formulario.
- Inclúyanlo en el JSON del POST desde JavaScript.
- Valídenlo en el backend: ausente o negativo debe devolver 400.
- Muestren el stock en la tabla y en las respuestas GET/POST.
- Conserven el funcionamiento del nombre y el precio.

Pistas: sigan el recorrido del campo `precio`; investiguen la diferencia entre `int` e `Integer` para detectar un dato ausente. Anoten las decisiones que tomen y verifiquen los casos.

**Pruebas obligatorias:** stock 5 y 0 se aceptan; stock -1 y ausente se rechazan con 400. Después de un rechazo, comprueben con GET que no se agregó el producto. Para enviar valores que el formulario no permita o quitar una propiedad, pueden editar temporalmente el objeto que construye `app.js` y recargar. Restauren el código después de probar.

**Ampliación opcional, después de terminar:** agregar una consulta por ID y responder 404 cuando el producto no exista. El proyecto base no incluye ese endpoint.

## 7. Guardar y entregar
Hagan commits con mensajes que expliquen el cambio. Revisen `git status` y `git diff` antes de confirmar. No suban `target/` ni configuraciones con rutas personales.

Entreguen:
1. Enlace al repositorio del grupo con el código modificado y los integrantes identificados.
2. Documento Word breve, de 2 a 4 páginas, usando las preguntas de `ENTREGA.md`.
3. Exposición de 5 a 7 minutos: ejecutar, demostrar un caso correcto y uno inválido, señalar el código y explicar el recorrido del nuevo dato. Cada integrante debe participar y responder preguntas.

## Problemas frecuentes
- **Java no encontrado / JAVA_HOME incorrecto:** el JDK debe estar instalado y configurado; abran otra terminal después del ajuste.
- **Puerto 8080 ocupado:** detengan la otra ejecución o cambien `server.port` y usen ese mismo puerto en el navegador.
- **No descarga dependencias:** revisen conexión y mensajes de Maven; no borren archivos del proyecto al azar.
- **Permission denied al ejecutar mvnw (macOS/Linux):** ejecuten `chmod +x mvnw`.
- **No aparece un cambio:** reinicien la aplicación y recarguen la página; revisen la terminal y la consola del navegador.
- **La página abre pero la petición falla:** comprueben que están usando la URL del servidor y que sigue en ejecución.

## Fuentes oficiales
- Spring Boot y Maven: https://docs.spring.io/spring-boot/installing.html
- Primera aplicación: https://spring.io/guides/gs/spring-boot
- API REST: https://spring.io/guides/gs/rest-service
- Páginas dentro de Spring Boot: https://spring.io/guides/gs/serving-web-content
- Maven Wrapper: https://maven.apache.org/tools/wrapper/
- Introducción al POM: https://maven.apache.org/guides/introduction/introduction-to-the-pom.html
- Git: https://git-scm.com/book/es/v2
