// clientes.js
import { get, post } from './api.js';

const tbody = document.getElementById('clientes-body');
const form  = document.getElementById('cliente-form');

document.addEventListener('DOMContentLoaded', () => {

  // Carga inicial
  loadClientes();

  async function loadVendedores() {
  try {
    const vendedores = await get('vendedores');
    const sel = document.getElementById('vendedor');
    vendedores.forEach(v => {
      const opt = document.createElement('option');
      opt.value = v.id;
      opt.textContent = v.nombre;
      sel.appendChild(opt);
    });
  } catch (e) {
    console.error('No se pudieron cargar los vendedores', e);
  }
}

loadVendedores();

  // Maneja el submit del formulario
  form.addEventListener('submit', async e => {
    e.preventDefault();

    const payload = {
      nombreLocal:   document.getElementById('nombreLocal').value.trim(),
      nombreContacto: document.getElementById('nombreContacto').value.trim() || null,
      telefono:      document.getElementById('telefono').value.trim()      || null,
      cuitCuil:      document.getElementById('cuitCuil').value.trim()      || null,
      direccion:     document.getElementById('direccion').value.trim(),
      localidadId:   document.getElementById('localidadId').value,
      vendedorId:      document.getElementById('vendedor').value.trim()      || null
    };

    // Validación básica de campos obligatorios
    if (!payload.nombreLocal || !payload.direccion || !payload.localidadId) {
      return alert('Completa todos los campos obligatorios (local, dirección y localidad).');
    }

    try {
      await post('clientes', payload);
      form.reset();
      loadClientes();
    } catch (err) {
      console.error(err);
      alert('Error al agregar cliente.');
    }
  });
});

/**
 * Descarga la lista de clientes y los renderiza en la tabla.
 */
export async function loadClientes() {
  try {
    const clientes = await get('clientes');
    tbody.innerHTML = '';

    clientes.forEach(c => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${c.id}</td>
        <td>${c.nombreLocal}</td>
        <td>${c.nombreContacto ?? ''}</td>
        <td>${c.telefono      ?? ''}</td>
        <td>${c.cuitCuil      ?? ''}</td>
        <td>${c.direccion}</td>
        <td>${c.localidadId}</td>
        <td>${(c.deudaTotal || 0).toFixed(2)}</td>
        <td>${c.vendedor ? c.vendedor.nombre : ''}</td>
        <td>
          <!-- Aquí podrás añadir botones de Editar/Eliminar -->
        </td>
      `;
      tbody.appendChild(tr);
    });
  } catch (err) {
    console.error(err);
    alert('No se pudieron cargar los clientes.');
  }
}

