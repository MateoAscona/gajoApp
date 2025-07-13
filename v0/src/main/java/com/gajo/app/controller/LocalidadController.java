package com.gajo.app.controller;

import com.gajo.app.model.Localidad;
import com.gajo.app.service.LocalidadService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localidades")
@CrossOrigin(origins="*")   // habilita CORS
public class LocalidadController {
    private final LocalidadService service;
    public LocalidadController(LocalidadService service){this.service=service;}

    @GetMapping
    public List<Localidad> getAll(){
        return service.findAll();
    }
}

