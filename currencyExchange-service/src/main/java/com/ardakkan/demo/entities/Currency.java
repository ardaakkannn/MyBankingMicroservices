package com.ardakkan.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_code", nullable = false, unique = true)
    private String currencyCode;  // Örneğin: "USD", "TRY", "EUR"

    @Column(name = "exchange_rate_to_try", nullable = false)
    private Double exchangeRateToTry;  // Bu para biriminin TRY karşısındaki değeri

    // Default constructor
    public Currency() {}

    // Parametreli constructor
    public Currency(String currencyCode, Double exchangeRateToTry) {
        this.currencyCode = currencyCode;
        this.exchangeRateToTry = exchangeRateToTry;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "Currency{" +
                "id=" + id +
                ", currencyCode='" + currencyCode + '\'' +
                ", exchangeRateToTry=" + exchangeRateToTry +
                '}';
    }
}

