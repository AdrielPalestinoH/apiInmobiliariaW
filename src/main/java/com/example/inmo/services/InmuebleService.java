package com.example.inmo.services;


import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.mappers.InmuebleMapper;
import com.example.inmo.models.EstadoInmueble;
import com.example.inmo.models.Inmueble;
import com.example.inmo.models.TipoInmueble;
import com.example.inmo.repositories.EstadoInmuebleRepository;
import com.example.inmo.repositories.InmuebleRepository;
import com.example.inmo.repositories.TipoInmuebleRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InmuebleService {
    private final InmuebleRepository inmuebleRepository;
    private final EstadoInmuebleRepository estadoRepo;
    private final TipoInmuebleRepository tipoRepo;

    public InmuebleService(InmuebleRepository inmuebleRepository,
                           EstadoInmuebleRepository estadoRepo,
                           TipoInmuebleRepository tipoRepo) {
        this.inmuebleRepository = inmuebleRepository;
        this.estadoRepo = estadoRepo;
        this.tipoRepo = tipoRepo;
    }

    public List<InmuebleDTO> listarInmuebles() {
        return inmuebleRepository.findAll()
                .stream()
                .map(InmuebleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InmuebleDTO crearInmueble(InmuebleDTO dto, Long estadoId, Long tipoId) {
        EstadoInmueble estado = estadoRepo.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        TipoInmueble tipo = tipoRepo.findById(tipoId)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        Inmueble inmueble = InmuebleMapper.toEntity(dto, estado, tipo);
        inmueble.setFechaAlta(LocalDateTime.now());

        Inmueble guardado = inmuebleRepository.save(inmueble);
        return InmuebleMapper.toDTO(guardado);
    }
}
