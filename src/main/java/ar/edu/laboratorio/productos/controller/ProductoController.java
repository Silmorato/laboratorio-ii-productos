package ar.edu.laboratorio.productos.controller;

import ar.edu.laboratorio.productos.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    // Almacenamiento temporal: se reinicia cuando se apaga la aplicación.
    private final List<Producto> productos = new ArrayList<>();
    private long siguienteId = 3;

    // Spring crea este controller al iniciar: cargamos dos ejemplos una sola vez.
    public ProductoController() {
        productos.add(new Producto(1L, "Cuaderno", 2500.0));
        productos.add(new Producto(2L, "Lapicera", 800.0));
    }

    // Atiende GET /api/productos: consultar sin agregar productos.
    @GetMapping
    public synchronized List<Producto> listar() {
        return new ArrayList<>(productos); // Crea una copia de la lista; Spring la convierte a JSON.
    }

    // Atiende POST /api/productos: validar y agregar un producto.
    @PostMapping
    public synchronized ResponseEntity<?> agregar(@RequestBody Producto producto) {
        // @RequestBody convierte el cuerpo JSON en un objeto Producto.
        // null: dato ausente; isBlank(): nombre vacío o compuesto solo por espacios.
        // badRequest() establece el estado 400; body() define el contenido de la respuesta.
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El nombre es obligatorio."));
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "El precio debe ser mayor que cero."));
        }
        producto.setId(siguienteId++); // El servidor asigna el identificador.
        producto.setNombre(producto.getNombre().trim());
        productos.add(producto);
        // 201 significa "Creado". En el cuerpo devolvemos el producto con su ID.
        return ResponseEntity.status(201).body(producto);
    }
}
