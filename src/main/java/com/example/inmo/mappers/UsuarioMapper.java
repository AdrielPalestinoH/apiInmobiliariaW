package com.example.inmo.mappers;

import com.example.inmo.dto.UsuarioDTO;
import com.example.inmo.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // 🔹 Convierte de entidad → DTO
    @Mapping(source = "tipoUsuario.id", target = "tipoUsuarioId")
    @Mapping(source = "tipoUsuario.descripcion", target = "tipoUsuarioDescripcion")
    UsuarioDTO toDto(Usuario usuario);

    // 🔹 Convierte de DTO → entidad (sin mapear tipoUsuario porque lo asignamos en el servicio)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toEntity(UsuarioDTO dto);
}
