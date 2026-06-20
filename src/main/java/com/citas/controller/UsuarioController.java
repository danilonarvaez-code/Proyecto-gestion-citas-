package com.citas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // Simplifica los imports de annotations
import com.citas.entity.Usuario;
import com.citas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // Opcional: Evita problemas de CORS si te conectas desde el frontend
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

    // PUT: Modificar un usuario existente (¡ESTO ES LO QUE TE FALTABA!)
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario u) {
        // Aquí idealmente seteas el ID al objeto antes de guardar para asegurarte de que actualice
        // u.setId(id); 
        return service.guardar(u); // O service.actualizar(u) si tienes un método específico
    }

    // DELETE: Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}