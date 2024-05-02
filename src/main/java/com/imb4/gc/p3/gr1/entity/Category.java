package com.imb4.gc.p3.gr1.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_category")
	private Long id_category;
	@Column(name= "nombre_category")
	private String nombre_category;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="fecha_creacion")
	private Date fecha_creacion;
	@Column(name="fecha_actualizacion")
	private Date fecha_actualizacion;
			
	
	
	public Category(Long id_category, String nombre_category, String descripcion, Date fecha_creacion,
			Date fecha_actualizacion) {
		this.id_category = id_category;
		this.nombre_category = nombre_category;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.fecha_actualizacion = fecha_actualizacion;
	}

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
