package com.citas.entity;

import jakarta.persistence.*;

@Entity
public class Cita {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String fecha;
private String descripcion;

@ManyToOne
@JoinColumn(name="usuario_id")
private Usuario usuario;

public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public String getFecha(){return fecha;}
public void setFecha(String fecha){this.fecha=fecha;}
public String getDescripcion(){return descripcion;}
public void setDescripcion(String descripcion){this.descripcion=descripcion;}
public Usuario getUsuario(){return usuario;}
public void setUsuario(Usuario usuario){this.usuario=usuario;}
}
