package com.imb4.gc.p3.gr1.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category extends BaseEntity{
  
  @NotBlank(message = "El nombre de la categoría no puede estar en blanco")
	private String nombreCategory;

	@NotBlank(message = "La descripción de la categoría no puede estar en blanco")
	private String descripcion;
	
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Category() {	}
	
	@ManyToMany
	private List<Product> products;
	
	public String getNombre_category() {
		return nombreCategory;
	}
	public void setNombre_category(String nombre_category) {
		this.nombreCategory = nombre_category;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_creacion() {
		return fechaCreacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fechaCreacion = fecha_creacion;
	}
	public Date getFecha_actualizacion() {
		return fechaActualizacion;
	}
	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fechaActualizacion = fecha_actualizacion;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
