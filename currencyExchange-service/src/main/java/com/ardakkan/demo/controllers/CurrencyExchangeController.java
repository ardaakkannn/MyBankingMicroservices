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

    // Get value of spesific Currency against TRY
    @GetMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDto> getExchangeRateToTry(@PathVariable String currencyCode) {
        try {
            CurrencyDto currencyDto = currencyExchangeService.getExchangeRateToTry(currencyCode);
            return new ResponseEntity<>(currencyDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    // Make Currency conversion using TRY as a reference
    @GetMapping("/convert")
    public ResponseEntity<Double> convertCurrency(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam Double amount) {
        
        try {
            Double convertedAmount = currencyExchangeService.convert(fromCurrency, toCurrency, amount);
            return new ResponseEntity<>(convertedAmount, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }
    
    // For manually adding Currency
    @PostMapping("/add")
    public ResponseEntity<CurrencyDto> addCurrency(@RequestBody CurrencyDto currencyDto) {
        CurrencyDto savedCurrency = currencyExchangeService.addCurrency(currencyDto);
        return new ResponseEntity<>(savedCurrency, HttpStatus.CREATED);  // 201 Created
    }
}
