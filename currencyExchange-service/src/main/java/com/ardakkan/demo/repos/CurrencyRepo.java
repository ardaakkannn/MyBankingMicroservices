package com.ardakkan.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ardakkan.demo.entities.Currency;

import java.util.Optional;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCurrencyCode(String currencyCode);
}

