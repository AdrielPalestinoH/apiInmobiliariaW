package com.example.inmo.services;


import com.example.inmo.dto.UsuarioDTO;
import com.example.inmo.mappers.UsuarioMapper;
import com.example.inmo.models.Usuario;
import com.example.inmo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO guardar(UsuarioDTO dto) {
        Usuario entity = usuarioMapper.toEntity(dto);
        return usuarioMapper.toDto(usuarioRepository.save(entity));
    }
}
