package com.example.inmo.mappers;


import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.models.EstadoInmueble;
import com.example.inmo.models.Inmueble;
import com.example.inmo.models.TipoInmueble;

public class InmuebleMapper {
    public static InmuebleDTO toDTO(Inmueble inmueble) {
        InmuebleDTO dto = new InmuebleDTO();
        dto.setId(inmueble.getId());
        dto.setPrecio(inmueble.getPrecio());
        dto.setDescripcion(inmueble.getDescripcion());
        dto.setFechaAlta(inmueble.getFechaAlta());
        dto.setEstadoDescripcion(
            inmueble.getEstadoInmueble() != null ? inmueble.getEstadoInmueble().getDescripcion() : null
        );
        dto.setTipoDescripcion(
            inmueble.getTipoInmueble() != null ? inmueble.getTipoInmueble().getDescripcion() : null
        );
        return dto;
    }
    
    public static Inmueble toEntity(InmuebleDTO dto, EstadoInmueble estado, TipoInmueble tipo) {
        Inmueble inmueble = new Inmueble();
        inmueble.setId(dto.getId());
        inmueble.setPrecio(dto.getPrecio());
        inmueble.setDescripcion(dto.getDescripcion());
        inmueble.setFechaAlta(dto.getFechaAlta());
        inmueble.setEstadoInmueble(estado);
        inmueble.setTipoInmueble(tipo);
        return inmueble;
    }
}
