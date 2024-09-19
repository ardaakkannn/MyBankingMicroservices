package com.ardakkan.demo.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CurrencyDto {

	
	
	@NotNull
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters long")
    private String currencyCode;
	@NotNull(message = "Exchange rate is required")
    private Double exchangeRateToTry;

    // Default constructor
    public CurrencyDto() {}

    // Parametreli constructor
    public CurrencyDto(String currencyCode, Double exchangeRateToTry) {
        this.currencyCode = currencyCode;
        this.exchangeRateToTry = exchangeRateToTry;
    }

    // Getters and setters
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getExchangeRateToTry() {
        return exchangeRateToTry;
    }

    public void setExchangeRateToTry(Double exchangeRateToTry) {
        this.exchangeRateToTry = exchangeRateToTry;
    }

    // toString method
    @Override
    public String toString() {
        return "CurrencyDto{" +
                "currencyCode='" + currencyCode + '\'' +
                ", exchangeRateToTry=" + exchangeRateToTry +
                '}';
    }
}

