package com.example.inmo.controllers;


import com.example.inmo.dto.CreditoDTO;
import com.example.inmo.services.CreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditoController {
    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping
    public List<CreditoDTO> listar() {
        return creditoService.listar();
    }

    @PostMapping
    public CreditoDTO crear(@RequestBody CreditoDTO dto) {
        return creditoService.crearCredito(dto);
    }
    
    @PutMapping("/{id}/reestructurar")
    public ResponseEntity<String> reestructurar(
            @PathVariable Long id,
            @RequestParam int nuevoTotalPagos,
            @RequestParam(required = false, defaultValue = "MENSUAL") String frecuencia) {

        creditoService.reestructurarPagos(id, nuevoTotalPagos, frecuencia);
        return ResponseEntity.ok("Crédito reestructurado correctamente");
    }
    

    // ✅ Listar créditos por usuario (cliente)
    @GetMapping("/usuario/{usuarioId}")
    public List<CreditoDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return creditoService.listarPorUsuario(usuarioId);
    }
}
