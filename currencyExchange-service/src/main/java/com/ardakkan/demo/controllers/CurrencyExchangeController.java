package com.ardakkan.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ardakkan.demo.dtos.CurrencyDto;
import com.ardakkan.demo.services.CurrencyExchangeService;

@RestController
@RequestMapping("/api/exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    // Belirli bir para biriminin TRY karşısındaki kurunu al
    @GetMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDto> getExchangeRateToTry(@PathVariable String currencyCode) {
        try {
            CurrencyDto currencyDto = currencyExchangeService.getExchangeRateToTry(currencyCode);
            return new ResponseEntity<>(currencyDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Kur bulunamazsa 404 döner
        }
    }

    // Bir para biriminden başka bir para birimine belirli bir miktarda çeviri yap
    @GetMapping("/convert")
    public ResponseEntity<Double> convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam Double amount) {
        
        try {
            Double convertedAmount = currencyExchangeService.convert(fromCurrency, toCurrency, amount);
            return new ResponseEntity<>(convertedAmount, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Dönüştürülemeyen bir hata durumunda 400 döner
        }
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<CurrencyDto> addCurrency(@RequestBody CurrencyDto currencyDto) {
        CurrencyDto savedCurrency = currencyExchangeService.addCurrency(currencyDto);
        return new ResponseEntity<>(savedCurrency, HttpStatus.CREATED);  // 201 Created
    }
}
