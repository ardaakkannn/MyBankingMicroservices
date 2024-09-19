package com.ardakkan.demo.services;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ardakkan.demo.dtos.AccountDto;

import java.util.List;

@FeignClient(name = "account-service")  // Account Service'in kayıtlı adı
public interface AccountServiceClient {

    @GetMapping("/api/accounts/customer/{customerId}")
    List<AccountDto> getAccountsByCustomerId(@PathVariable("customerId") Long customerId);
}

