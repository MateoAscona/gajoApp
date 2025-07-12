// main.js

import { loadClientes } from './clientes.js';
import { loadProductos } from './productos.js';

// Seleccionamos enlaces y secciones
const links = document.querySelectorAll('aside a[data-target]');
const sections = document.querySelectorAll('main section');

/**
 * Activa (resalta) el enlace dado y desactiva el resto
 */
function activateLink(link) {
  links.forEach(l => l.classList.remove('active'));
  link.classList.add('active');
}

/**
 * Muestra la sección cuyo id coincide con target, oculta las demás,
 * y dispara el loader correspondiente.
 */
function showSection(target) {
  sections.forEach(sec => {
    if (sec.id === target) {
      sec.classList.add('active');
      // Cargar datos según sección
      if (target === 'clientes-section') {
        loadClientes();
      } else if (target === 'productos-section') {
        loadProductos();
      }
    } else {
      sec.classList.remove('active');
    }
  });
}

// Cuando clickeo en un enlace de la sidebar:
links.forEach(link => {
  link.addEventListener('click', event => {
    event.preventDefault();
    activateLink(link);
    showSection(link.dataset.target);
  });
});

// Al cargar el DOM, simulamos el “click” en el primer enlace:
document.addEventListener('DOMContentLoaded', () => {
  if (links.length) {
    activateLink(links[0]);
    showSection(links[0].dataset.target);
  }
});
