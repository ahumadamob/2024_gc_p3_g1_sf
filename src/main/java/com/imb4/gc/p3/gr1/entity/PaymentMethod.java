package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_payment_method")
	private Long id_payment_method;
	
	@Column(name="type")
	private String type;
	
	@Column(name="data")
	private String data;
	
	@Column(name="total")
	private Float total;
	
	public PaymentMethod(){}
	
	public PaymentMethod(Long id_payment_method, String type, String data, Float total) {
		super();
		this.id_payment_method = id_payment_method;
		this.type = type;
		this.data = data;
		this.total = total;
	}

	public Long getId_payment_method() {
		return id_payment_method;
	}

	public void setId_payment_method(Long id_payment_method) {
		this.id_payment_method = id_payment_method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
}
