package com.gajo.app.repository;

import com.gajo.app.model.Deuda;
import com.gajo.app.model.Remito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeudaRepository extends JpaRepository<Deuda,Integer> {
    Optional<Deuda> findByRemito(Remito remito);
}
