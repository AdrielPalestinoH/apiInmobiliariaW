package com.example.inmo.controllers;

import com.example.inmo.dto.PagoDTO;
import com.example.inmo.models.*;
import com.example.inmo.repositories.*;
import com.example.inmo.services.CreditoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final CreditoService creditoService;
    private final CreditoDetalleRepository detalleRepo;
    private final CreditoAbonoDetalleRepository abonoRepo;
    private final MetodoPagoRepository metodoRepo;

    public PagoController(CreditoService creditoService,
                          CreditoDetalleRepository detalleRepo,
                          CreditoAbonoDetalleRepository abonoRepo,
                          MetodoPagoRepository metodoRepo) {
        this.creditoService = creditoService;
        this.detalleRepo = detalleRepo;
        this.abonoRepo = abonoRepo;
        this.metodoRepo = metodoRepo;
    }

    // ✅ Registrar un pago (abono)
   @PostMapping
   public ResponseEntity<PagoDTO> registrarPago(@RequestBody PagoDTO dto) {
    CreditoDetalle detalle = detalleRepo.findById(dto.getCreditoDetalleId())
            .orElseThrow(() -> new RuntimeException("Detalle de crédito no encontrado"));
    MetodoPago metodo = metodoRepo.findById(dto.getMetodoPagoId())
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

    CreditoAbonoDetalle abono = new CreditoAbonoDetalle();
    abono.setCredito(detalle.getCredito());
    abono.setCreditoDetalle(detalle);
    abono.setMetodoPago(metodo);
    abono.setTotalAbonado(dto.getTotalAbonado());
    abono.setReferencia(dto.getReferencia());
    abono.setFechaPago(LocalDateTime.now());

    abonoRepo.save(abono);

    detalle.setEstatus(1);
    detalleRepo.save(detalle);

    // 👇 devolvemos un DTO simple (sin relaciones LAZY)
    PagoDTO respuesta = new PagoDTO();
    respuesta.setId(abono.getId());
    respuesta.setCreditoDetalleId(detalle.getId());
    respuesta.setMetodoPagoId(metodo.getId());
    respuesta.setTotalAbonado(abono.getTotalAbonado());
    respuesta.setReferencia(abono.getReferencia());
    respuesta.setFechaPago(abono.getFechaPago());

    return ResponseEntity.ok(respuesta);
}

    // ✅ Listar métodos de pago
    @GetMapping("/metodos")
    public List<MetodoPago> listarMetodosPago() {
        return metodoRepo.findAll();
    }

  
    @GetMapping("/{creditoId}")
    public ResponseEntity<List<CreditoDetalle>> listarPorCredito(@PathVariable Long creditoId) {
        Credito credito = creditoService.buscarPorId(creditoId);
        List<CreditoDetalle> detalles = detalleRepo.findByCredito(credito);
        return ResponseEntity.ok(detalles);
    }
    
}
