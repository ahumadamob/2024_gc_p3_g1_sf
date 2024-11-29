package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
public class Cart extends BaseEntity {

    @PositiveOrZero
    @NotNull
    private float total; // Total del carrito (calculado automáticamente).

    @ManyToOne
    @NotNull
    private User user; // Usuario asociado al carrito.

    @ManyToMany
    @JoinTable(
        name = "cart_product", // Tabla intermedia para la relación muchos a muchos.
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @NotNull(message = "El carrito debe tener al menos un producto.")
    private List<Product> products; // Lista de productos.

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder; // Relación con una orden de compra (opcional, solo al confirmar).

    @ManyToOne
    private PaymentMethod paymentMethod; // Método de pago (opcional al crear, obligatorio al confirmar).

    @NotNull
    private Boolean active = true; // Indica si el carrito está activo (por defecto, verdadero).

    // Getters y setters.
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
