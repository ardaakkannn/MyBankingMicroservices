package com.ardakkan.demo.dtos;

public class AccountDto {

    private Long id;
    private String accountType;
    private String currency;
    private Double balance;
    private Long customerId;

    // Default constructor
    public AccountDto() {
    }

    // Parametreli constructor
    public AccountDto(Long id, String accountType, String currency, Double balance, Long customerId) {
        this.id = id;
        this.accountType = accountType;
        this.currency = currency;
        this.balance = balance;
        this.customerId = customerId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // toString method
    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}

