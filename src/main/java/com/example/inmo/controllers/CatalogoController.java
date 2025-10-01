package com.example.inmo.controllers;

import com.example.inmo.models.EstadoInmueble;
import com.example.inmo.models.TipoInmueble;
import com.example.inmo.services.CatalogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@CrossOrigin(origins = "http://localhost:4200")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/estados")
    public List<EstadoInmueble> listarEstados() {
        return catalogoService.listarEstados();
    }

    @GetMapping("/tipos")
    public List<TipoInmueble> listarTipos() {
        return catalogoService.listarTipos();
    }
}
