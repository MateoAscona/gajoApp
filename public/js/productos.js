// productos.js
import { get, post } from './api.js';

const tbodyP = document.getElementById('productos-body');
const formP  = document.getElementById('producto-form');

document.addEventListener('DOMContentLoaded', () => {
  // Carga inicial
  loadProductos();

  // Manejo de envío del formulario
  formP.addEventListener('submit', async e => {
    e.preventDefault();

    const payload = {
      nombre:      document.getElementById('prodNombre').value.trim(),
      categoria:   document.getElementById('prodCategoria').value.trim() || null,
      peso:        parseFloat(document.getElementById('prodPeso').value),
      costoCompra: parseFloat(document.getElementById('prodCosto').value),
      precioVenta: parseFloat(document.getElementById('prodPrecio').value),
     stockActual: parseInt(document.getElementById('prodStock').value)
    };

    // Validación básica
    if (!payload.nombre || isNaN(payload.peso) || isNaN(payload.costoCompra) || isNaN(payload.precioVenta) || isNaN(payload.stockActual)) {
      return alert('Completa nombre, peso, costo y precio correctamente.');
    }

    try {
      await post('productos', payload);
      formP.reset();
      await loadProductos();
    } catch (err) {
      console.error(err);
      alert('Error al agregar producto.');
    }
  });
});

/**
 * Descarga el listado de productos y lo pinta en la tabla.
 */
export async function loadProductos() {
  try {
    const productos = await get('productos');
    tbodyP.innerHTML = '';

    productos.forEach(p => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${p.id}</td>
        <td>${p.nombre}</td>
        <td>${p.categoria ?? ''}</td>
        <td>${p.peso.toFixed(2)}</td>
        <td>${p.costoCompra.toFixed(2)}</td>
        <td>${p.precioVenta.toFixed(2)}</td>
        <td>${p.stockActual ?? 0}</td>
      `;
      tbodyP.appendChild(tr);
    });
  } catch (err) {
    console.error(err);
    alert('No se pudieron cargar los productos.');
  }
}
