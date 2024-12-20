package com.imb4.gc.p3.gr1.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product extends BaseEntity{

	@ManyToMany
	private List<Cart> carts;
	@NotEmpty
	private String name;
	@NotNull
	private String description;
	@PositiveOrZero
	private Float price;
	@PositiveOrZero
	private int stock;
	@NotBlank
	private String images;
	@NotNull
	private String category;
	@ManyToMany
	private List<Category> categories;
	@OneToMany(mappedBy = "product")
	@JsonIgnore
    private List<CartProduct> cartsProduct;
	@Positive
	private int quantity;
	@OneToMany
	private List<Rating> ratings; 
	
	private boolean destacado;
	
	
	
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Product() {}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isDestacado() {
		return destacado;
	}

	public void setDestacado(boolean destacado) {
		this.destacado = destacado;
	}

	public List<CartProduct> getCartsProduct() {
		return cartsProduct;
	}

	public void setCartsProduct(List<CartProduct> cartsProduct) {
		this.cartsProduct = cartsProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
