package com.example.inmo.repositories;

import com.example.inmo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar queries personalizadas, ej:
    Usuario findByEmail(String email);
}
