package com.imb4.gc.p3.gr1.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Category extends BaseEntity{

	private String nombre_category;
	private String descripcion;
	private Date fecha_creacion;
	private Date fecha_actualizacion;

	public Category() {	}
	
	@ManyToMany
	private List<Product> products;
	
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
