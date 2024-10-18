package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Entity;

@Entity
public class Rating extends BaseEntity{

    private String user;
    private String product;
    private String opinion;
    private int rating;

    public Rating() {}

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