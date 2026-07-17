package com.citas.repository;

import com.citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    // Método personalizado de Spring Data JPA: 
    // Te permitirá buscar de manera automática todas las citas de un paciente usando su ID
    List<Cita> findByUsuarioId(Long usuarioId);
}