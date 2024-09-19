package com.ardakkan.demo.dtos;

public class DepositRequestDto {

    private Long accountId;
    private Double amount;

    // Default constructor
    public DepositRequestDto() {}

    // Parametreli constructor
    public DepositRequestDto(Long accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    // Getters and setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

