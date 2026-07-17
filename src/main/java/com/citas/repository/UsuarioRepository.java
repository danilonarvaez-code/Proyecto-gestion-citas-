package com.citas.repository;

import com.citas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Al heredar de JpaRepository, Spring ya sabe qué hacer con la entidad Usuario usando su ID (Long)
}
