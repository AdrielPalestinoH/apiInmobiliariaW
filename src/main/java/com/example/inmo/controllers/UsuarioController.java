package com.example.inmo.controllers;


import com.example.inmo.dto.UsuarioDTO;
import com.example.inmo.models.Usuario;
import com.example.inmo.services.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200") // 👈 habilita Angular

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO dto) {
        return usuarioService.guardar(dto);
    }
    
    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        dto.setId(id);
        return usuarioService.actualizar(dto);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginRequest) {
        Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getPwd());

        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // Construimos una respuesta simple
        Map<String, Object> response = new HashMap<>();
        response.put("id", usuario.getId());
        response.put("nombre", usuario.getNombre());
        response.put("email", usuario.getEmail());
        response.put("tipoUsuarioId", usuario.getTipoUsuario().getId());
        response.put("tipoUsuarioNombre", usuario.getTipoUsuario().getDescripcion());

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public UsuarioDTO registrarCliente(@RequestBody UsuarioDTO dto) {
        dto.setTipoUsuarioId(2L); // 👈 tipo 2 = cliente
        return usuarioService.guardar(dto);
    }

}
