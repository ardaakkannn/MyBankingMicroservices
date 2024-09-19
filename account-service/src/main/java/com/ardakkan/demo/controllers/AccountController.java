package com.ardakkan.demo.controllers;

import com.ardakkan.demo.dtos.AccountDto;
import com.ardakkan.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Creating new Account
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);  // 201 Created
    }

    // Listing all Accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);  // 200 OK
    }

    // Finding Account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        Optional<AccountDto> accountDtoOptional = accountService.getAccountById(id);
        return accountDtoOptional.map(accountDto -> new ResponseEntity<>(accountDto, HttpStatus.OK))  // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // 404 Not Found
    }

    // Finding accounts by Customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountDto>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<AccountDto> accounts = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);  // 200 OK
    }

    // Account Updating
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        try {
            AccountDto updatedAccount = accountService.updateAccount(id, accountDto);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);  // 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }

    //Deleting Account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
      
    //Decreasing Balance of spesific Account
    }
    @PostMapping("/{id}/decreaseBalance")
    public ResponseEntity<String> decreaseBalance(@PathVariable Long id, @RequestParam Double amount) {
        accountService.decreaseBalance(id, amount);
        return new ResponseEntity<>("Balance decreased", HttpStatus.OK);
    }
    //Increasing Balance of spesific Account
    @PostMapping("/{id}/increaseBalance")
    public ResponseEntity<String> increaseBalance(@PathVariable Long id, @RequestParam Double amount) {
        accountService.increaseBalance(id, amount);
        return new ResponseEntity<>("Balance increased", HttpStatus.OK);
    }
    
    
}
