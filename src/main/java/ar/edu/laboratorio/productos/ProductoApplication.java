package ar.edu.laboratorio.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductoApplication {

	public static void main(String[] args) {
		// Inicia Spring Boot y el servidor web para recibir peticiones.
		SpringApplication.run(ProductoApplication.class, args);
	}

}
