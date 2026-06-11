package com.citas.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citas.entity.Usuario;
import com.citas.repository.UsuarioRepository;

@Service
public class UsuarioService {
@Autowired
private UsuarioRepository repo;

public List<Usuario> listar(){return repo.findAll();}
public Usuario guardar(Usuario u){return repo.save(u);}
public void eliminar(Long id){repo.deleteById(id);}
}
