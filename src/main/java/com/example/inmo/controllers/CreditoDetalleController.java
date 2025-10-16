package com.example.inmo.controllers;
 

import com.example.inmo.models.Credito;
import com.example.inmo.models.CreditoDetalle;
import com.example.inmo.repositories.CreditoDetalleRepository;
import com.example.inmo.repositories.CreditoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditoDetalleController {

    private final CreditoRepository creditoRepo;
    private final CreditoDetalleRepository detalleRepo;

    public CreditoDetalleController(CreditoRepository creditoRepo, CreditoDetalleRepository detalleRepo) {
        this.creditoRepo = creditoRepo;
        this.detalleRepo = detalleRepo;
    }

    @GetMapping("/{id}/detalles")
    public List<CreditoDetalle> listarDetalles(@PathVariable Long id) {
        Credito credito = creditoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado"));
        return detalleRepo.findByCredito(credito);
    }
}
