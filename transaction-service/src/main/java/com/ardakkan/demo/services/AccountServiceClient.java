package com.ardakkan.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ardakkan.demo.dtos.AccountDto;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    // Belirli bir hesap ID'sine göre hesabı al
    @GetMapping("/api/accounts/{id}")
    AccountDto getAccountById(@PathVariable("id") Long id);

    // Hesaptan bakiye azalt
    @PostMapping("/api/accounts/{id}/decreaseBalance")
    void decreaseBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount);

    // Hesaba bakiye ekle
    @PostMapping("/api/accounts/{id}/increaseBalance")
    void increaseBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount);
}

