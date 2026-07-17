package com.citas.service;

import com.citas.entity.Usuario;
import com.citas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Obtener todos los usuarios (pacientes)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // 2. Obtener un usuario por su ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // 3. Guardar o registrar un nuevo usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // 4. Eliminar un usuario por su ID
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}