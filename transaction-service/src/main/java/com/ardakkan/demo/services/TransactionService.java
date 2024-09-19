package com.ardakkan.demo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardakkan.demo.dtos.AccountDto;
import com.ardakkan.demo.dtos.TransactionDto;
import com.ardakkan.demo.entities.Transaction;
import com.ardakkan.demo.repos.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private AccountServiceClient accountServiceClient;  
    @Autowired
    private TransactionRepository transactionRepository; 
    @Autowired
    private CurrencyExchangeService currencyExchangeService;  

    
    // Through atm only TRY can be deposit or withdraw
    //If customer want to make operations on USD or EUR account, currency is calculated accordingly
    
    
    public TransactionDto transfer(Long senderAccountId, Long receiverAccountId, Double amount) {
        AccountDto senderAccount = accountServiceClient.getAccountById(senderAccountId);
        AccountDto receiverAccount = accountServiceClient.getAccountById(receiverAccountId);

        
        if (!senderAccount.getCurrency().equals(receiverAccount.getCurrency())) {
            throw new RuntimeException("Currency mismatch: Can only transfer between accounts with the same currency.");
        }

        
        if (senderAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in sender's account.");
        }

        
        accountServiceClient.decreaseBalance(senderAccountId, amount);
        accountServiceClient.increaseBalance(receiverAccountId, amount);

        
        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(senderAccountId);
        transaction.setReceiverAccountId(receiverAccountId);
        transaction.setAmount(amount);
        transaction.setTransactionType("TRANSFER");
        transaction.setCurrency(senderAccount.getCurrency());
        transaction.setTransactionDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    
    public TransactionDto deposit(Long accountId, Double amount) {
        AccountDto account = accountServiceClient.getAccountById(accountId);

        
        if (account.getCurrency().equals("USD")) {
            
            Double exchangedAmount = currencyExchangeService.convert("TRY", "USD", amount);
            accountServiceClient.increaseBalance(accountId, exchangedAmount);  
        } else if (account.getCurrency().equals("TRY")) {
            accountServiceClient.increaseBalance(accountId, amount);  
        } else {
            throw new RuntimeException("Invalid currency for deposit.");
        }

        // Save transaction
        Transaction transaction = new Transaction();
        transaction.setReceiverAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setCurrency("TRY");
        transaction.setTransactionDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    
    public TransactionDto withdraw(Long accountId, Double amount) {
        AccountDto account = accountServiceClient.getAccountById(accountId);

        
        if (!account.getCurrency().equals("TRY")) {
            throw new RuntimeException("ATM withdrawals are only allowed in TRY.");
        }

        
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for withdrawal.");
        }

        
        accountServiceClient.decreaseBalance(accountId, amount);

        
        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(accountId);  
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setCurrency("TRY");
        transaction.setTransactionDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    // Entity -> DTO
    private TransactionDto mapToDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getSenderAccountId(),
                transaction.getReceiverAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getCurrency(),
                transaction.getTransactionDate()
        );
    }
}

