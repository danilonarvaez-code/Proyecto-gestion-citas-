package com.citas.controller;

import com.citas.entity.Cita;
import com.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas") // Ruta base para el agendamiento y gestión de citas
public class CitaController {

    @Autowired
    private CitaService citaService;

    // 1. GET: http://localhost:8080/api/citas (Listar todas las citas)
    @GetMapping
    public List<Cita> listarTodas() {
        return citaService.obtenerTodas();
    }

    // 2. GET por ID: http://localhost:8080/api/citas/{id} (Buscar una cita específica)
    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        try {
            return citaService.obtenerPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 3. GET por Paciente: http://localhost:8080/api/citas/paciente/{usuarioId}
    @GetMapping("/paciente/{usuarioId}")
    public ResponseEntity<List<Cita>> listarPorPaciente(@PathVariable Long usuarioId) {
        List<Cita> citas = citaService.obtenerCitasPorPaciente(usuarioId);
        return ResponseEntity.ok(citas);
    }

    // 4. POST: http://localhost:8080/api/citas (Agendar una nueva cita)
    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.agendarCita(cita);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            // Si el usuario no existe o faltan datos, devuelve un error 400 Bad Request con el mensaje explicativo
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. PUT: http://localhost:8080/api/citas/{id} (Modificar o cambiar estado de la cita)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cita citaActualizada) {
        try {
            Cita citaModificada = citaService.actualizarCita(id, citaActualizada);
            return ResponseEntity.ok(citaModificada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 6. DELETE: http://localhost:8080/api/citas/{id} (Eliminar una cita)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            citaService.eliminar(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}