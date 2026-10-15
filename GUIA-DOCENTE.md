# Guía breve para la docente

Presentación sugerida: “Este es el trabajo integrador que comenzamos en esta clase, continuamos en la siguiente y después exponemos. Retoma Java, estructura de proyectos, HTTP, Git y GitHub. Cada grupo agregará stock y deberá demostrar y explicar cómo viaja ese dato. Los contenidos trabajados forman parte del examen”.

Trabajar por etapas del README, sin corte obligatorio entre días. Antes de la clase, probar la descarga y ejecución con la red del aula. La primera descarga puede llevar tiempo. El proyecto usa JDK 21 y Spring Boot 4.1.1, versión entregada por Spring Initializr al prepararlo.

Primera demostración: GET → POST correcto → GET → POST inválido. Pedir que predigan cada resultado antes de ejecutar. Después recorrer Application, modelo y controller. Presentar pom.xml como un archivo para investigar con preguntas concretas, no como conocimiento previo supuesto.

Para grupos que necesitan apoyo, seguir el campo precio de punta a punta antes de agregar stock. Para grupos que avanzan más rápido, ofrecer la búsqueda por ID una vez completadas las pruebas obligatorias.

Reservar tiempo en la segunda clase para entrega y exposiciones. Multiplicar cantidad de grupos por 7 minutos para decidir si se necesita otro bloque. Hacer una pregunta individual por integrante.

El precio usa Double por simplicidad didáctica; en una aplicación monetaria real se suele usar BigDecimal. La lista en memoria y la lógica dentro del controller también son simplificaciones explícitas. No hace falta introducir base de datos, autenticación ni capas adicionales para este integrador.

Repositorio público de la actividad: https://github.com/Silmorato/laboratorio-ii-productos. Compartir esa URL con los grupos. La consigna stock está pendiente deliberadamente: es el trabajo de los estudiantes.


La lectura guiada del README explicita los elementos que deben investigar. Se retiraron el test generado y su dependencia para reducir el material. Se simplificó la validación del precio a dato obligatorio y mayor que cero; no se estudian valores numéricos especiales. El 201 significa Creado; 200 significa OK. El constructor carga los ejemplos al iniciar, y el método POST agrega los nuevos productos al recibir peticiones.
