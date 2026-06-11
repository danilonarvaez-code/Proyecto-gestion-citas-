package com.citas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citas.entity.Cita;
public interface CitaRepository extends JpaRepository<Cita, Long>{}
