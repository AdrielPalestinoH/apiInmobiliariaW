package com.example.inmo.repositories;

import com.example.inmo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {
	
	
}