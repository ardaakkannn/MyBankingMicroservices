package com.ardakkan.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeService {

    @GetMapping("/api/exchange/convert")
    Double convert(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam Double amount);
}
