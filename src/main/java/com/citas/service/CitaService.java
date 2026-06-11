package com.citas.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citas.entity.Cita;
import com.citas.repository.CitaRepository;

@Service
public class CitaService {
@Autowired
private CitaRepository repo;

public List<Cita> listar(){return repo.findAll();}
public Cita guardar(Cita c){return repo.save(c);}
public void eliminar(Long id){repo.deleteById(id);}
}
