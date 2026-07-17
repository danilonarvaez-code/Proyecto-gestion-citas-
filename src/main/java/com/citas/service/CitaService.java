package com.citas.service;

import com.citas.entity.Cita;
import com.citas.entity.Usuario;
import com.citas.repository.CitaRepository;
import com.citas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Obtener todas las citas del sistema
    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    // 2. Obtener una cita específica por su ID
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    // 3. Obtener el historial de citas de un paciente específico
    public List<Cita> obtenerCitasPorPaciente(Long usuarioId) {
        return citaRepository.findByUsuarioId(usuarioId);
    }

    // 4. Agendar una nueva cita con validación de usuario
    public Cita agendarCita(Cita cita) {
        // Validamos si el objeto usuario viene en la petición y tiene un ID
        if (cita.getUsuario() == null || cita.getUsuario().getId() == null) {
            throw new IllegalArgumentException("La cita debe estar asociada a un usuario con ID válido.");
        }

        // Buscamos al usuario en la base de datos para asegurar que existe
        Long usuarioId = cita.getUsuario().getId();
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("No se puede agendar la cita: El usuario con ID " + usuarioId + " no existe."));

        // Asignamos el usuario real encontrado al objeto cita
        cita.setUsuario(usuarioExistente);
        
        // Por defecto, toda cita nueva se crea en estado "ASIGNADA"
        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("ASIGNADA");
        }

        return citaRepository.save(cita);
    }

    // 5. Cancelar o modificar una cita
    public Cita actualizarCita(Long id, Cita citaActualizada) {
        return citaRepository.findById(id).map(cita -> {
            if (citaActualizada.getFechaHora() != null) {
                cita.setFechaHora(citaActualizada.getFechaHora());
            }
            if (citaActualizada.getEspecialidad() != null) {
                cita.setEspecialidad(citaActualizada.getEspecialidad());
            }
            if (citaActualizada.getEstado() != null) {
                cita.setEstado(citaActualizada.getEstado());
            }
            return citaRepository.save(cita);
        }).orElseThrow(() -> new IllegalArgumentException("No se encontró la cita con ID: " + id));
    }

    // 6. Eliminar una cita del sistema
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}