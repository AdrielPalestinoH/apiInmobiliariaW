package com.example.inmo.repositories;

import com.example.inmo.models.Credito;
import com.example.inmo.models.CreditoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditoDetalleRepository extends JpaRepository<CreditoDetalle, Long> {
    List<CreditoDetalle> findByCreditoAndEstatus(Credito credito, Integer estatus);
    // 🔹 obtiene todos los pagos (detalles) de un crédito específico
    List<CreditoDetalle> findByCredito(Credito credito);
    
    List<CreditoDetalle> findByCreditoAndEstatus(Credito credito, int estatus);

}
