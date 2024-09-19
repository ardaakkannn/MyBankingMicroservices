package com.ardakkan.demo.dtos;

public class AddressDto {

    private Long id;
    private String country;
    private String city;
    private String district;
    private String street;
    private String zipCode;

    // Default constructor
    public AddressDto() {
    	
    }

    // Parametreli constructor
    public AddressDto(Long id, String country, String city, String district, String street, String zipCode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // toString 
    @Override
    public String toString() {
        return "AddressDTO [id=" + id + ", country=" + country + ", city=" + city + ", district=" + district + ", street=" + street
                + ", zipCode=" + zipCode + "]";
    }
}

