package com.example.inmo.services;


import com.example.inmo.models.TipoUsuario;
import com.example.inmo.repositories.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioService {
    private final TipoUsuarioRepository repo;

    public TipoUsuarioService(TipoUsuarioRepository repo) {
        this.repo = repo;
    }

    public List<TipoUsuario> listar() {
        return repo.findAll();
    }
}
