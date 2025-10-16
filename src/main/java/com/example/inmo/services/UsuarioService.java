package com.example.inmo.services;


import com.example.inmo.dto.UsuarioDTO;
import com.example.inmo.mappers.UsuarioMapper;
import com.example.inmo.models.TipoUsuario;
import com.example.inmo.models.Usuario;
import com.example.inmo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    

    
    
    public UsuarioDTO actualizar(UsuarioDTO dto) {
        Usuario existente = usuarioRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setApellidos(dto.getApellidos());
        existente.setEmail(dto.getEmail());
        existente.setCel(dto.getCel());

        Usuario actualizado = usuarioRepository.save(existente);
        return usuarioMapper.toDto(actualizado);
    }

    
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO guardar(UsuarioDTO dto) {
        Usuario entity = usuarioMapper.toEntity(dto);

        // Asignar tipo_usuario = 2 (cliente)
        TipoUsuario tipoCliente = new TipoUsuario();
        tipoCliente.setId(2L);
        entity.setTipoUsuario(tipoCliente);

        return usuarioMapper.toDto(usuarioRepository.save(entity));
    }

    
    public Usuario autenticar(String email, String password) {
        Optional<Usuario> usuarioOpt = Optional.ofNullable(usuarioRepository.findByEmail(email));

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // ⚠️ Aquí comparamos el password directamente (sin encriptar)
            // Si después quieres seguridad real, podemos implementar BCrypt
            if (usuario.getPwd().equals(password)) {
                return usuario;
            }
        }

        return null;
    }
}
