package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
public class Rating extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @NotEmpty(message= "La opinión no puede estar vacía")
	private String opinion;
    
    @Positive
    private int note;
    private String user;
    private int rating;
    
    public Rating() {}


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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


	public void setApproved(boolean b) {
	
	}


	public String getNote() {
		return null;
	}

}
