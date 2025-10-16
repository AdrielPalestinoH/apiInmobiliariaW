package com.example.inmo.controllers;

import com.example.inmo.dto.InmuebleDTO;
import com.example.inmo.services.InmuebleService;
import org.springframework.web.bind.annotation.*;

import com.example.inmo.models.Inmueble;
import com.example.inmo.models.InmuebleContacto;
import com.example.inmo.repositories.InmuebleContactoRepository;
import com.example.inmo.repositories.InmuebleRepository;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/api/inmuebles")
@CrossOrigin(origins = "http://localhost:4200") // Para permitir llamadas desde Angular
public class InmuebleController {

	 private final InmuebleService inmuebleService;
	 
	 	private final InmuebleRepository inmuebleRepo;
	    private final InmuebleContactoRepository contactoRepo;

	    // ✅ Constructor completo con las tres dependencias
	    public InmuebleController(InmuebleService inmuebleService,
	                              InmuebleRepository inmuebleRepo,
	                              InmuebleContactoRepository contactoRepo) {
	        this.inmuebleService = inmuebleService;
	        this.inmuebleRepo = inmuebleRepo;
	        this.contactoRepo = contactoRepo;
	    }
	    
	    @GetMapping
	    public List<InmuebleDTO> listarInmuebles() {
	        return inmuebleService.listarInmuebles();
	    }

	    @PostMapping
	    public InmuebleDTO crear(@RequestBody InmuebleDTO dto) {
	        return inmuebleService.crearInmueble(dto);
	    }
	    
	    
	    @PutMapping("/{id}")
	    public InmuebleDTO actualizar(@PathVariable Long id, @RequestBody InmuebleDTO dto) {
	        dto.setId(id);
	        return inmuebleService.actualizar(dto);
	    }

	    
	    @GetMapping("/{id}")
	    public Optional<Inmueble> obtenerPorId(@PathVariable Long id) {
	        return inmuebleRepo.findById(id);
	    }

	    // 🔹 Guardar solicitud de contacto
	    @PostMapping("/{id}/contacto")
	    public InmuebleContacto guardarContacto(@PathVariable Long id, @RequestBody InmuebleContacto contacto) {
	        Inmueble inmueble = inmuebleRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Inmueble no encontrado"));

	        contacto.setInmueble(inmueble);
	        return contactoRepo.save(contacto);
	    }

}
