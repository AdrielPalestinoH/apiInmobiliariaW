package com.example.inmo.services;

import com.example.inmo.dto.CreditoDTO;
import com.example.inmo.models.*;
import com.example.inmo.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors; 


@Service
public class CreditoService {

    private final CreditoRepository creditoRepo;
    private final UsuarioRepository usuarioRepo;
    private final InmuebleRepository inmuebleRepo;
    private final CreditoDetalleRepository detalleRepo;

    public CreditoService(CreditoRepository creditoRepo,
                          UsuarioRepository usuarioRepo,
                          InmuebleRepository inmuebleRepo,
                          CreditoDetalleRepository detalleRepo) {
        this.creditoRepo = creditoRepo;
        this.usuarioRepo = usuarioRepo;
        this.inmuebleRepo = inmuebleRepo;
        this.detalleRepo = detalleRepo;
    }

    // ✅ LISTAR TODOS LOS CRÉDITOS
    public List<CreditoDTO> listar() {
        return creditoRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public Credito buscarPorId(Long id) {
        return creditoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado con ID: " + id));
    }

    // ✅ CREAR CRÉDITO NUEVO
    public CreditoDTO crearCredito(CreditoDTO dto) {
        Credito credito = new Credito();

        credito.setTotal(dto.getTotal());
        credito.setTotalPagos(dto.getTotalPagos());
        credito.setEstatus("ACTIVO");
        credito.setReferencia(dto.getReferencia());
        credito.setFecha(LocalDateTime.now());
        credito.setAutorizacion(true);

        // 🕓 Manejo de fechas
        credito.setFechaInicio(dto.getFechaInicio() != null
                ? dto.getFechaInicio().atStartOfDay()
                : LocalDateTime.now());

        credito.setFechaFinal(dto.getFechaFinal() != null
                ? dto.getFechaFinal().atStartOfDay()
                : null);

        // 🔗 Relaciones
        credito.setUsuario(usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        credito.setInmueble(inmuebleRepo.findById(dto.getInmuebleId())
                .orElseThrow(() -> new RuntimeException("Inmueble no encontrado")));

        // 💾 Guardar crédito base
        credito = creditoRepo.save(credito);

        // 🧾 Generar pagos automáticos
        generarKardexPagos(credito, dto.getFrecuencia());

        return toDTO(credito);
    }

    // ✅ GENERAR PAGOS AUTOMÁTICOS
    private void generarKardexPagos(Credito credito, String frecuencia) {
        int totalPagos = credito.getTotalPagos();
        if (totalPagos <= 0) return;

        double montoPorPago = credito.getTotal() / totalPagos;
        LocalDate fechaInicio = credito.getFechaInicio().toLocalDate();

        for (int i = 0; i < totalPagos; i++) {
            CreditoDetalle detalle = new CreditoDetalle();
            detalle.setCredito(credito);
            detalle.setEstatus(0); // 0 = pendiente
            detalle.setPrecio(montoPorPago);

            // Calcular fecha según frecuencia
            LocalDate fechaProgramada = switch (frecuencia != null ? frecuencia.toUpperCase() : "MENSUAL") {
                case "SEMANAL" -> fechaInicio.plusWeeks(i);
                case "QUINCENAL" -> fechaInicio.plusWeeks(i * 2L);
                case "MENSUAL" -> fechaInicio.plusMonths(i);
                default -> fechaInicio.plusMonths(i);
            };

            detalle.setFechaProgramada(fechaProgramada);
            detalleRepo.save(detalle);
        }
    }

    // ✅ REESTRUCTURAR PAGOS (solo los pendientes)
    public void reestructurarPagos(Long creditoId, int nuevoTotalPagos) {
        Credito credito = creditoRepo.findById(creditoId)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado"));

        List<CreditoDetalle> pendientes = detalleRepo.findByCreditoAndEstatus(credito, 0);
        detalleRepo.deleteAll(pendientes);

        credito.setTotalPagos(nuevoTotalPagos);
        creditoRepo.save(credito);

        generarKardexPagos(credito, "MENSUAL");
    }

    // ✅ MAPEO DTO
    private CreditoDTO toDTO(Credito credito) {
        CreditoDTO dto = new CreditoDTO();

        dto.setId(credito.getId());
        dto.setTotal(credito.getTotal());
        dto.setTotalPagos(credito.getTotalPagos());
        dto.setEstatus(credito.getEstatus());
        dto.setReferencia(credito.getReferencia());
        dto.setFrecuencia("MENSUAL");

        // ✅ Relación con Usuario
        if (credito.getUsuario() != null) {
            dto.setUsuarioId(credito.getUsuario().getId());
            dto.setUsuarioNombre(
                credito.getUsuario().getNombre() + " " + credito.getUsuario().getApellidos()
            );
        }

        // ✅ Relación con Inmueble
        if (credito.getInmueble() != null) {
            dto.setInmuebleId(credito.getInmueble().getId());
            dto.setInmuebleDescripcion(credito.getInmueble().getDescripcion());
        }
        
        // 🕓 Manejo de fechas seguro
        if (credito.getFechaInicio() != null)
            dto.setFechaInicio(credito.getFechaInicio().toLocalDate());
        if (credito.getFechaFinal() != null)
            dto.setFechaFinal(credito.getFechaFinal().toLocalDate());

        // 🔗 Relaciones
        dto.setUsuarioId(credito.getUsuario() != null ? credito.getUsuario().getId() : null);
        dto.setInmuebleId(credito.getInmueble() != null ? credito.getInmueble().getId() : null);

        return dto;
    }
    
    
    public void reestructurarPagos(Long creditoId, int nuevoTotalPagos, String frecuencia) {
        Credito credito = creditoRepo.findById(creditoId)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado"));

        List<CreditoDetalle> pendientes = detalleRepo.findByCreditoAndEstatus(credito, 0);
        detalleRepo.deleteAll(pendientes); // elimina solo los pendientes

        credito.setTotalPagos(nuevoTotalPagos);
        creditoRepo.save(credito);

        generarKardexPagos(credito, frecuencia); // usa la frecuencia seleccionada
    }
    
    public List<CreditoDTO> listarPorUsuario(Long usuarioId) {
        return creditoRepo.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
 
}
