package com.citas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guardará la fecha y la hora exacta de la cita médica
    private LocalDateTime fechaHora;

    private String especialidad; // Ej: Medicina General, Odontología, etc.
    private String estado;       // Ej: ASIGNADA, CANCELADA, COMPLETADA

    // RELACIÓN: Muchas citas pertenecen a un único Usuario (Paciente)
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Crea la llave foránea en la tabla citas
    private Usuario usuario;

    // --- CONSTRUCTORES ---
    public Cita() {
    }

    public Cita(Long id, LocalDateTime fechaHora, String especialidad, String estado, Usuario usuario) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.especialidad = especialidad;
        this.estado = estado;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String specialty) {
        this.especialidad = specialty;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
