package com.imb4.gc.p3.gr1.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class PurchaseOrder extends BaseEntity{
    
    @NotEmpty(message = "La fecha no puede ser nula ni estar vacía")
    private String date;
    
    @NotEmpty(message = "El estado no puede ser nulo ni estar vacío")
    private String state;
    
    @NotEmpty(message = "La dirección no puede ser nula ni estar vacía")
    @Size(min=3, max=70, message="La dirección debe tener entre 3 y 70 caracteres")
    private String address;
    
    @NotNull(message="El total no puede ser nulo")
    @Positive(message="El total debe ser un valor positivo")
    private double total;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<Cart> carts;
    
    @NotEmpty(message = "El método de envío no puede ser nulo ni vacío")
    private String shippingMethod;

    private String estimatedDeliveryDate;

	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public PurchaseOrder() {}

    public String getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
