package com.example.inmo.repositories;

import com.example.inmo.models.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
	
	
	List<Credito> findByUsuarioId(Long usuarioId);
}