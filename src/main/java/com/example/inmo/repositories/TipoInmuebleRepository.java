package com.example.inmo.repositories;

import com.example.inmo.models.TipoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoInmuebleRepository extends JpaRepository<TipoInmueble, Long> {
    TipoInmueble findByDescripcion(String descripcion);
}
