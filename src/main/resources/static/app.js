// El navegador ejecuta JavaScript; el servidor ejecuta Java.
const ruta = '/api/productos';
const mostrar = (id, texto) => document.getElementById(id).textContent = texto;

// Si no pasamos un producto, no enviamos un cuerpo: es el caso del GET.
async function enviar(metodo, producto = null) {
    document.querySelectorAll('button').forEach(b => b.disabled = true);
    mostrar('peticion', metodo + ' ' + ruta + (metodo === 'POST' ? '\nContent-Type: application/json' : ''));
    mostrar('enviado', producto === null ? 'Sin cuerpo: este GET no envía JSON.' : JSON.stringify(producto, null, 2));
    mostrar('estado', 'Esperando respuesta…');
    mostrar('respuesta', '—');
    try {
        const opciones = { method: metodo };
        if (producto !== null) {
            opciones.headers = { 'Content-Type': 'application/json' };
            opciones.body = JSON.stringify(producto); // Objeto JavaScript → texto JSON.
        }
        const respuesta = await fetch(ruta, opciones);
        const datos = await respuesta.json(); // Texto JSON → datos en JavaScript.
        mostrar('estado', 'Estado HTTP: ' + respuesta.status + (respuesta.ok ? ' · Operación exitosa' : ' · Revisá los datos enviados'));
        mostrar('respuesta', JSON.stringify(datos, null, 2));
        if (metodo === 'GET' && respuesta.ok) {
            const tabla = document.getElementById('productos');
            tabla.replaceChildren();
            datos.forEach(producto => {
                const fila = document.createElement('tr');
                [producto.id, producto.nombre, producto.precio].forEach(valor => {
                    const celda = document.createElement('td');
                    celda.textContent = valor;
                    fila.appendChild(celda);
                });
                tabla.appendChild(fila);
            });
        }
    } catch (error) {
        mostrar('estado', 'No se pudo completar la petición. Verificá que el servidor siga encendido.');
        mostrar('respuesta', error.message);
    } finally {
        document.querySelectorAll('button').forEach(b => b.disabled = false);
    }
}

document.getElementById('consultar').addEventListener('click', () => enviar('GET'));
document.getElementById('formulario').addEventListener('submit', evento => {
    evento.preventDefault();
    const valorPrecio = document.getElementById('precio').value;
    const producto = {
        nombre: document.getElementById('nombre').value,
        precio: valorPrecio === '' ? null : Number(valorPrecio)
    };
    enviar('POST', producto);
});
