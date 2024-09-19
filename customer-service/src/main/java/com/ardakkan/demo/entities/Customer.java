package com.ardakkan.demo.entities;

import java.util.List;

import com.ardakkan.demo.dtos.AccountDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="Customer")
public class Customer {
	

	@Id
	@GeneratedValue
	@Column(name="customer_id")
	private Long id;
	
	@Column(name="cutomer_tcId",unique=true,nullable = false)
	private String tcId;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
	
	@Column(name="customer_name",nullable = false)
	private String name;
	
	@Column(name="customer_surname",nullable = false)
	private String surname;
	
	@Email(message = "Email is not valid")
	@Column(name="customer_email",nullable = false,unique=true)
	private String email;
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String tcId, Address address, String name, String surname,
			@Email(message = "Email is not valid") String email) {
		super();
		this.id = id;
		this.tcId = tcId;
		this.address = address;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	



	@Override
	public String toString() {
		return "Customer [id=" + id + ", tcId=" + tcId + ", address=" + address + ", name=" + name + ", surname="
				+ surname + ", email=" + email + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTcId() {
		return tcId;
	}

	public void setTcId(String tcId) {
		this.tcId = tcId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}