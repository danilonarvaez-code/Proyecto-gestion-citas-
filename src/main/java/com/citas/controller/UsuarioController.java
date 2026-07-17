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
@RequestMapping("/api/usuarios") // Ruta base para todos los servicios de usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET: http://localhost:8080/api/usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // GET por ID: http://localhost:8080/api/usuarios/{id}
    @ゲットMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get()); // Retorna 200 OK con el usuario
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no existe
    }

    // POST: http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardar(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // Retorna 201 Created
    }

    // DELETE: http://localhost:8080/api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content (eliminado con éxito)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encontró
    }
}