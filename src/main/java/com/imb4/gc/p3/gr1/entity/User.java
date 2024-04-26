package com.imb4.gc.p3.gr1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private Long id_user;
    
	@Column(name="name")
	private String name;
    
	@Column(name="lastname")
	private String lastname;
    
	@Column(name="email")
	private String email;
    
	@Column(name="password")
	private String password;
    
	@Column(name="phone")
	private String phone;

	
	
	@Column(name="address")
    private String address;
    
	@Column(name="role")
    private String role;
	

    //Constructor
	public User(String role, String email, Long idUser, String password, String address, String name, String phone,
			String lastname) {
		super();
		this.role = role;
		this.email = email;
		this.id_user = idUser;
		this.password = password;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.lastname = lastname;
	}
	
	//Setters-Getters
	public Long getIdUser() {
		return id_user;
	}
	public void setIdUser(Long idUser) {
		this.id_user = idUser;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
