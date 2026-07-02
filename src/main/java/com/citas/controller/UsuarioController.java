package com.citas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.citas.entity.Usuario;
import com.citas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // GET: Listar todos
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    // POST: Guardar un nuevo usuario
    @PostMapping
    public Usuario guardar(@RequestBody Usuario u) {
        return service.guardar(u);
    }

    // PUT: Modificar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario u) {
        u.setId(id);
        return service.guardar(u);
    }

    // DELETE: Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}