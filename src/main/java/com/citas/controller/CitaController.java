package com.citas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.citas.entity.Cita;
import com.citas.service.CitaService;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    // GET: http://localhost:8080/citas
    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }

    // POST: http://localhost:8080/citas
    @PostMapping
    public Cita guardar(@RequestBody Cita c) {
        return service.guardar(c);
    }

    // PUT: http://localhost:8080/citas/{id}
    @PutMapping("/{id}")
public Cita actualizar(@PathVariable Long id, @RequestBody Cita c) {
    c.setId(id); // <-- ESTA LÍNEA ES OBLIGATORIA para que sepa qué cita actualizar
    return service.guardar(c);                                                                                                                   
}

    // DELETE: http://localhost:8080/citas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}