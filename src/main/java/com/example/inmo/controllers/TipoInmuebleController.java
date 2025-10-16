package com.example.inmo.controllers;


import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.models.TipoInmueble;
import com.example.inmo.repositories.TipoInmuebleRepository;
import com.example.inmo.services.InmuebleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-inmueble")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoInmuebleController {
    private final TipoInmuebleRepository tipoRepo;

    public TipoInmuebleController(TipoInmuebleRepository tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    @GetMapping
    public List<TipoInmueble> listar() {
        return tipoRepo.findAll();
    }
}
