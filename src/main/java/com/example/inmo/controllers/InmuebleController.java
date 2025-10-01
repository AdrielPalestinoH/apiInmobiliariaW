package com.example.inmo.controllers;

import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.services.InmuebleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inmuebles")
@CrossOrigin(origins = "http://localhost:4200") // Para permitir llamadas desde Angular
public class InmuebleController {

	 private final InmuebleService inmuebleService;

	    public InmuebleController(InmuebleService inmuebleService) {
	        this.inmuebleService = inmuebleService;
	    }

	    @GetMapping
	    public List<InmuebleDTO> listarInmuebles() {
	        return inmuebleService.listarInmuebles();
	    }

	    @PostMapping
	    public InmuebleDTO crearInmueble(@RequestBody InmuebleDTO dto,
	                                     @RequestParam Long estadoId,
	                                     @RequestParam Long tipoId) {
	        return inmuebleService.crearInmueble(dto, estadoId, tipoId);
	    }
}
