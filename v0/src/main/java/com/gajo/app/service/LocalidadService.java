package com.gajo.app.service;

import com.gajo.app.model.Localidad;
import com.gajo.app.repository.LocalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocalidadService {
    List<Localidad> findAll();
}

