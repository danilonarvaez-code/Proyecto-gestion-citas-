package com.citas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.citas.entity.Cita;
import com.citas.service.CitaService;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "http://localhost:5173")
public class CitaController {

    @Autowired
    private CitaService service;

    // GET: Listar todas las citas
    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }

    // POST: Guardar una nueva cita
    @PostMapping
    public Cita guardar(@RequestBody Cita c) {
        // Validación de seguridad para la relación ManyToOne
        if (c.getUsuario() != null && c.getUsuario().getId() == null) {
            c.setUsuario(null);
        }
        return service.guardar(c);
    }

    // PUT: Actualizar una cita existente
    @PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id, @RequestBody Cita c) {
        c.setId(id); 
        return service.guardar(c);                                                                                                                                                 
    }

    // DELETE: Eliminar una cita
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}