package com.citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.citas.entity.Cita;
import com.citas.repository.CitaRepository;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:5173")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        return citaRepository.findById(id)
                .map(cita -> ResponseEntity.ok().body(cita))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cita guardar(@RequestBody Cita cita) {
        return citaRepository.save(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita citaDetalles) {
        return citaRepository.findById(id)
                .map(citaExistente -> {
                    citaExistente.setFechaHora(citaDetalles.getFechaHora());
                    citaExistente.setEspecialidad(citaDetalles.getEspecialidad());
                    citaExistente.setUsuario(citaDetalles.getUsuario());
                    Cita actualizada = citaRepository.save(citaExistente);
                    return ResponseEntity.ok().body(actualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        return citaRepository.findById(id)
                .map(cita -> {
                    citaRepository.delete(cita);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}