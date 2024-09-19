package com.ardakkan.demo.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;

public class CustomerDto {

    private Long id;
    private String tcId;
    private AddressDto address;
    private String name;
    private String surname;
    
    @Email(message = "Email is not valid")
    private String email;
    private List<AccountDto> accounts;

    // Default constructor
    public CustomerDto() {
    }

    // Parametreli constructor
    public CustomerDto(Long id, String tcId, AddressDto address, String name, String surname, String email,List<AccountDto> accounts) {
        this.id = id;
        this.tcId = tcId;
        this.address = address;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accounts=accounts;
    }
    

    public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}

	// Getters ve Setters
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
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

    // toString 
    @Override
    public String toString() {
        return "CustomerDTO [id=" + id + ", tcId=" + tcId + ", address=" + address + ", name=" + name + ", surname="
                + surname + ", email=" + email + "]";
    }
}
