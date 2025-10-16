package com.example.inmo.controllers;
 

import com.example.inmo.dto.AbonoDTO;
import com.example.inmo.models.*;
import com.example.inmo.repositories.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/abonos")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditoAbonoController {

    private final CreditoAbonoDetalleRepository abonoRepo;
    private final CreditoDetalleRepository detalleRepo;
    private final MetodoPagoRepository metodoPagoRepo;

    public CreditoAbonoController(
            CreditoAbonoDetalleRepository abonoRepo,
            CreditoDetalleRepository detalleRepo,
            MetodoPagoRepository metodoPagoRepo) {
        this.abonoRepo = abonoRepo;
        this.detalleRepo = detalleRepo;
        this.metodoPagoRepo = metodoPagoRepo;
    }

    @PostMapping
    public CreditoAbonoDetalle registrarAbono(@RequestBody AbonoDTO dto) {
        CreditoDetalle detalle = detalleRepo.findById(dto.getCreditoDetalleId())
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        CreditoAbonoDetalle abono = new CreditoAbonoDetalle();
        abono.setCredito(detalle.getCredito());
        abono.setMetodoPago(metodoPagoRepo.findById(dto.getMetodoPagoId()).orElseThrow());
        abono.setTotalAbonado(dto.getTotalAbonado());
        abono.setFechaPago(LocalDateTime.now());
        abono.setReferencia(dto.getReferencia());

        abonoRepo.save(abono);

        // Actualizar estatus del pago si se liquidó
        if (dto.getTotalAbonado() >= detalle.getPrecio()) {
            detalle.setEstatus(1); // Pagado
            detalleRepo.save(detalle);
        }

        return abono;
    }
}
