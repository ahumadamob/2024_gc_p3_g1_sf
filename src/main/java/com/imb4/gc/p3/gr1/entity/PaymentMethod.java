package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El tipo no puede estar vacio")
	private String type;

	@NotEmpty(message = "El nombre no puede estar vacio")
	private String name;

	@NotEmpty(message = "El data no puede estar vacio")
	private String data;

	@NotNull(message = "El total no puede ser nulo")
	@Positive(message = "El total debe ser un valor positivo")
	private Float total;

	@NotNull(message = "El límite diario no puede ser nulo")
	@Positive(message = "El límite diario debe ser un valor positivo")
	private Float dailyLimit;

	@NotNull(message = "El límite por transacción no puede ser nulo")
	@Positive(message = "El límite por transacción debe ser un valor positivo")
	private Float transactionLimit;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Float getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(Float dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public Float getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(Float transactionLimit) {
		this.transactionLimit = transactionLimit;
	}
}
