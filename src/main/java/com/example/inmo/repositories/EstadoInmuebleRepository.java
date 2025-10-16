package com.example.inmo.repositories;

import com.example.inmo.models.EstadoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoInmuebleRepository extends JpaRepository<EstadoInmueble, Long> {
    EstadoInmueble findByDescripcion(String descripcion);
}
