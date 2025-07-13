package com.gajo.app.service;

import com.gajo.app.model.Localidad;
import com.gajo.app.repository.LocalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadServiceImpl implements LocalidadService {
    private final LocalidadRepository repo;
    public LocalidadServiceImpl(LocalidadRepository repo){this.repo=repo;}
    public List<Localidad> findAll(){return repo.findAll();}
}
