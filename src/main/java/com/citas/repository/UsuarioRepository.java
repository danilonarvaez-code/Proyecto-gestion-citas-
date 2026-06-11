package com.citas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.citas.entity.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{}
