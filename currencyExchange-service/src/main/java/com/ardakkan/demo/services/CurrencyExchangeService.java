package com.ardakkan.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardakkan.demo.dtos.CurrencyDto;
import com.ardakkan.demo.entities.Currency;
import com.ardakkan.demo.repos.CurrencyRepo;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyRepo currencyRepository;
    
    
 // Bir para birimini ekleme
    public CurrencyDto addCurrency(CurrencyDto currencyDto) {
        // DTO'yu Entity'ye dönüştür
        Currency currency = new Currency();
        currency.setCurrencyCode(currencyDto.getCurrencyCode());
        currency.setExchangeRateToTry(currencyDto.getExchangeRateToTry());

        // Veritabanına kaydet
        Currency savedCurrency = currencyRepository.save(currency);

        // Kaydedilen entity'yi DTO'ya dönüştür ve döndür
        return mapToDto(savedCurrency);
    }

    // Bir para biriminden TRY'ye çeviri oranını al
    public CurrencyDto getExchangeRateToTry(String currencyCode) {
        Currency currency = currencyRepository.findByCurrencyCode(currencyCode)
                .orElseThrow(() -> new RuntimeException("Currency not found: " + currencyCode));
        return mapToDto(currency);
    }

    // Para birimleri arasında çeviri yap (Örneğin TRY to USD)
    public Double convert(String fromCurrencyCode, String toCurrencyCode, Double amount) {
        Currency fromCurrency = currencyRepository.findByCurrencyCode(fromCurrencyCode)
                .orElseThrow(() -> new RuntimeException("Currency not found: " + fromCurrencyCode));
        Currency toCurrency = currencyRepository.findByCurrencyCode(toCurrencyCode)
                .orElseThrow(() -> new RuntimeException("Currency not found: " + toCurrencyCode));

        Double conversionRate = fromCurrency.getExchangeRateToTry() / toCurrency.getExchangeRateToTry();
        return amount * conversionRate;
    }

    // Entity -> DTO dönüşümü
    public CurrencyDto mapToDto(Currency currency) {
        return new CurrencyDto(currency.getCurrencyCode(), currency.getExchangeRateToTry());
    }
}

