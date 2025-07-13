package com.gajo.app.controller;

import com.gajo.app.dto.ClienteDto;
import com.gajo.app.model.Cliente;
import com.gajo.app.model.Vendedor;
import com.gajo.app.service.ClienteService;
import com.gajo.app.service.VendedorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;
    private final VendedorService vendedorService;

    public ClienteController(ClienteService clienteService,
                             VendedorService vendedorService) {
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Cliente> getAll(
            @RequestParam(required = false) Integer localidadId,
            @RequestParam(required = false) Integer vendedorId
    ) {
        // si me llegan parámetros, uso el método de filtros
        if (localidadId != null || vendedorId != null) {
            return clienteService.findByFilters(localidadId, vendedorId);
        }
        // otherwise devuelvo todo
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteDto dto) {
        // 1. Buscar vendedor
        Vendedor vend = vendedorService
                .listarTodos()
                .stream()
                .filter(v -> v.getId().equals(dto.getVendedorId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));

        // 2. Mapear DTO a entidad
        Cliente c = new Cliente();
        c.setNombreLocal(dto.getNombreLocal());
        c.setNombreContacto(dto.getNombreContacto());
        c.setTelefono(dto.getTelefono());
        c.setCuitCuil(dto.getCuitCuil());
        c.setDireccion(dto.getDireccion());
        // suponemos que tu entidad Cliente tiene campo localidadId
        c.setLocalidadId(dto.getLocalidadId());
        c.setVendedor(vend);

        // 3. Guardar y devolver
        Cliente saved = clienteService.save(c);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id,
                                          @RequestBody ClienteDto dto) {
        // Asegurar que exista el cliente
        Optional<Cliente> opt = clienteService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vendedor vend = vendedorService.findById(dto.getVendedorId())
                .orElseThrow(() -> new EntityNotFoundException("Vendedor no encontrado"));

        Cliente c = opt.get();
        c.setNombreLocal(dto.getNombreLocal());
        c.setNombreContacto(dto.getNombreContacto());
        c.setTelefono(dto.getTelefono());
        c.setCuitCuil(dto.getCuitCuil());
        c.setDireccion(dto.getDireccion());
        c.setLocalidadId(dto.getLocalidadId());
        c.setVendedor(vend);

        Cliente updated = clienteService.save(c);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


