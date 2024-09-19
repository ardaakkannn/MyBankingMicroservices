package com.ardakkan.demo.services;


import com.ardakkan.demo.dtos.AccountDto;
import com.ardakkan.demo.entity.Account;
import com.ardakkan.demo.repos.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountsRepo accountRepository;

    @Autowired
    public AccountService(AccountsRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Yeni hesap oluşturma (AccountDto döner)
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = mapToEntity(accountDto);  // DTO -> Entity
        Account savedAccount = accountRepository.save(account);
        return mapToDTO(savedAccount);  // Entity -> DTO
    }

    // Tüm hesapları listeleme (List<AccountDto> döner)
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ID ile hesap bulma (AccountDto döner)
    public Optional<AccountDto> getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.map(this::mapToDTO);  // Optional -> DTO
    }

    // Müşteri ID'sine göre hesapları listeleme (List<AccountDto> döner)
    public List<AccountDto> getAccountsByCustomerId(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return accounts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Hesap güncelleme (AccountDto döner)
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountType(accountDto.getAccountType());
            account.setCurrency(accountDto.getCurrency());
            account.setBalance(accountDto.getBalance());
            account.setCustomerId(accountDto.getCustomerId());
            Account updatedAccount = accountRepository.save(account);
            return mapToDTO(updatedAccount);
        } else {
            throw new RuntimeException("Account not found with id: " + id);
        }
    }

    // Hesap silme
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
    
    
    public void decreaseBalance(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void increaseBalance(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    // Entity -> DTO dönüşümü
    private AccountDto mapToDTO(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountType(),
                account.getCurrency(),
                account.getBalance(),
                account.getCustomerId()
        );
    }

    // DTO -> Entity dönüşümü
    private Account mapToEntity(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getAccountType(),
                accountDto.getCurrency(),
                accountDto.getBalance(),
                accountDto.getCustomerId()
        );
    }
}

