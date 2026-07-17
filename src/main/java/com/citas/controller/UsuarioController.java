package com.citas.controller;

import com.citas.entity.Usuario;
import com.citas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET: http://localhost:8080/api/usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // GET por ID: http://localhost:8080/api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST: http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // PUT: http://localhost:8080/api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            usuarioExistente.setNombre(usuarioDetalles.getNombre());
            usuarioExistente.setApellido(usuarioDetalles.getApellido());
            usuarioExistente.setDocumentoIdentidad(usuarioDetalles.getDocumentoIdentidad());
            usuarioExistente.setTelefono(usuarioDetalles.getTelefono());
            usuarioExistente.setCorreo(usuarioDetalles.getCorreo());
            
            Usuario usuarioActualizado = usuarioService.guardar(usuarioExistente);
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: http://localhost:8080/api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}