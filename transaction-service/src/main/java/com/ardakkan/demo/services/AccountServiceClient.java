package com.ardakkan.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ardakkan.demo.dtos.AccountDto;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    
    @GetMapping("/api/accounts/{id}")
    AccountDto getAccountById(@PathVariable("id") Long id);

    
    @PostMapping("/api/accounts/{id}/decreaseBalance")
    void decreaseBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount);

   
    @PostMapping("/api/accounts/{id}/increaseBalance")
    void increaseBalance(@PathVariable("id") Long id, @RequestParam("amount") Double amount);
}

