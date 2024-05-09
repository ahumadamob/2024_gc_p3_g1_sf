package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_opciones_valoraciones;
    private String user;
    private String product;
    private String opinion;
    private int rating;

    // Constructor vacío
    public Rating() {}

    // Constructor con todos los atríbutos
    public Rating(Long id_opciones_valoraciones, String user, String product, String opinion, int rating) {
        this.id_opciones_valoraciones = id_opciones_valoraciones;
        this.user = user;
        this.product = product;
        this.opinion = opinion;
        this.rating = rating;
    }
    // Getters y setters
    public Long getId_opciones_valoraciones() {
        return id_opciones_valoraciones;
    }

    public void setId_opciones_valoraciones(Long id_opciones_valoraciones) {
        this.id_opciones_valoraciones = id_opciones_valoraciones;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}