package com.example.inmo.mappers;


import com.example.inmo.dto.UsuarioDTO;
import com.example.inmo.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "tipoUsuario.descripcion", target = "tipoUsuarioDescripcion")
    UsuarioDTO toDto(Usuario usuario);

    @Mapping(target = "tipoUsuario", ignore = true) // lo llenamos en servicio
    Usuario toEntity(UsuarioDTO dto);
}
