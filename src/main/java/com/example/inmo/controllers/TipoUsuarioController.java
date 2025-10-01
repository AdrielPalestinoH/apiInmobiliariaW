package com.example.inmo.controllers;

import com.example.inmo.models.TipoUsuario;
import com.example.inmo.services.TipoUsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo/tipos-usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoUsuarioController {
    private final TipoUsuarioService service;

    public TipoUsuarioController(TipoUsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoUsuario> listar() {
        return service.listar();
    }
}
