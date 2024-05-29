package com.imb4.gc.p3.gr1.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_category;
	private String nombre_category;
	private String descripcion;
	private Date fecha_creacion;
	private Date fecha_actualizacion;

	public Category() {	}
	
	public Long getId_category() {
		return id_category;
	}
	public void setId_category(Long id_category) {
		this.id_category = id_category;
	}
	public String getNombre_category() {
		return nombre_category;
	}
	public void setNombre_category(String nombre_category) {
		this.nombre_category = nombre_category;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}
	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}
}
