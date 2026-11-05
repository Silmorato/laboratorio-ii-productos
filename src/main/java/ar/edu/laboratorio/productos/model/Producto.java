package ar.edu.laboratorio.productos.model;

// Modelo: representa los datos de un producto.
public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    // Integer permite distinguir un stock ausente (null) de cero unidades.
    private Integer stock;

    public Producto() {} // Permite crear un objeto a partir del JSON recibido.

    public Producto(Long id, String nombre, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
