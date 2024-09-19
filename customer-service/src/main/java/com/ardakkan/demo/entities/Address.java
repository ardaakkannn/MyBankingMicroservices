package com.ardakkan.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Address")
public class Address {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	private String country;
    @NotNull
	private String city;
    @NotNull
	private String district;
    @NotNull
	private String street;
    @NotNull
	private String zipCode;
	
	

	public Address(String country, String city, String district, String street, String zipCode) {
		super();
		this.country = country;
		this.city = city;
		this.district = district;
		this.street = street;
		this.zipCode = zipCode;
	}
	

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", district=" + district + ", street=" + street
				+ ", zipCode=" + zipCode + "]";
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
