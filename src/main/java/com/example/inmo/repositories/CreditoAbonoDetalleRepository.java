package com.example.inmo.repositories;

import com.example.inmo.models.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditoAbonoDetalleRepository extends JpaRepository<CreditoAbonoDetalle, Long> {
	
	   List<CreditoAbonoDetalle> findByCredito(Credito credito);
	   
	   @Query("SELECT SUM(c.totalAbonado) FROM CreditoAbonoDetalle c")
	   Double sumTotalAbonado();
}