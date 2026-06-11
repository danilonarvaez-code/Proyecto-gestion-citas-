package com.citas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.citas.entity.Cita;
import com.citas.service.CitaService;

@RestController
@RequestMapping("/citas")
public class CitaController {

@Autowired
private CitaService service;

@GetMapping
public List<Cita> listar(){return service.listar();}

@PostMapping
public Cita guardar(@RequestBody Cita c){return service.guardar(c);}

@DeleteMapping("/{id}")
public void eliminar(@PathVariable Long id){service.eliminar(id);}
}
