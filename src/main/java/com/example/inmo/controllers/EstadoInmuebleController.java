package com.example.inmo.controllers;




import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.models.EstadoInmueble;
import com.example.inmo.repositories.EstadoInmuebleRepository;
import com.example.inmo.services.InmuebleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/estados-inmueble")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoInmuebleController {
    private final EstadoInmuebleRepository estadoRepo;

    public EstadoInmuebleController(EstadoInmuebleRepository estadoRepo) {
        this.estadoRepo = estadoRepo;
    }

    @GetMapping
    public List<EstadoInmueble> listar() {
        return estadoRepo.findAll();
    }
}
