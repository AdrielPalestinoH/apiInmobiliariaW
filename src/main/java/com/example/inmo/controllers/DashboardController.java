package com.example.inmo.controllers;
 
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import com.example.inmo.repositories.UsuarioRepository;
import com.example.inmo.repositories.CreditoAbonoDetalleRepository;
import com.example.inmo.repositories.InmuebleRepository;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    private final UsuarioRepository usuarioRepo;
    private final CreditoAbonoDetalleRepository abonoRepo;
    private final InmuebleRepository inmuebleRepo;

    public DashboardController(
            UsuarioRepository usuarioRepo,
            CreditoAbonoDetalleRepository abonoRepo,
            InmuebleRepository inmuebleRepo) {
        this.usuarioRepo = usuarioRepo;
        this.abonoRepo = abonoRepo;
        this.inmuebleRepo = inmuebleRepo;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> data = new HashMap<>();

        // 🔹 Total de clientes (usuarios tipo 2)
        Long totalClientes = usuarioRepo.countByTipoUsuarioId(2L);


        // 🔹 Total de pagos (suma total_abonado)
        Double totalPagos = abonoRepo.sumTotalAbonado();
        if (totalPagos == null) totalPagos = 0.0;

        // 🔹 Total de inmuebles
        Long totalInmuebles = inmuebleRepo.count();

        data.put("clientes", totalClientes);
        data.put("pagos", totalPagos);
        data.put("inmuebles", totalInmuebles);

        return ResponseEntity.ok(data);
    }
}
