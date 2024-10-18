package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "payment_method")
public class PaymentMethod extends BaseEntity{
	
	@NotEmpty(message= "El tipo no puede estar vacio")
	private String type;
	
	@NotEmpty(message= "El nombre no puede estar vacio")
	private String name;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message= "El data no puede estar vacio")
	private String data;
	
	@NotNull(message="El total no puede ser nulo")
    @Positive(message="El total debe ser un valor positivo")
	private Float total;
	
	public PaymentMethod(){}


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
