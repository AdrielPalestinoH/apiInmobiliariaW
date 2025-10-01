package com.example.inmo.services;

 

import com.example.inmo.models.EstadoInmueble;
import com.example.inmo.models.TipoInmueble;
import com.example.inmo.repositories.EstadoInmuebleRepository;
import com.example.inmo.repositories.TipoInmuebleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {
    private final EstadoInmuebleRepository estadoRepo;
    private final TipoInmuebleRepository tipoRepo;

    public CatalogoService(EstadoInmuebleRepository estadoRepo, TipoInmuebleRepository tipoRepo) {
        this.estadoRepo = estadoRepo;
        this.tipoRepo = tipoRepo;
    }

    public List<EstadoInmueble> listarEstados() {
        return estadoRepo.findAll();
    }

    public List<TipoInmueble> listarTipos() {
        return tipoRepo.findAll();
    }
}
