package com.example.inmo.repositories;

import com.example.inmo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí puedes agregar queries personalizadas, ej:
    Usuario findByEmail(String email);
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.tipoUsuario.id = :tipoId")
    Long countByTipoUsuarioId(@Param("tipoId") Long tipoId);
}
