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
    private AccountServiceClient accountServiceClient;  // Feign Client ile Account servisine erişim
    @Autowired
    private TransactionRepository transactionRepository; // Transaction veritabanı kaydı için
    @Autowired
    private CurrencyExchangeService currencyExchangeService;  // Kur çevirim servisi

    // Para transferi işlemi
    public TransactionDto transfer(Long senderAccountId, Long receiverAccountId, Double amount) {
        AccountDto senderAccount = accountServiceClient.getAccountById(senderAccountId);
        AccountDto receiverAccount = accountServiceClient.getAccountById(receiverAccountId);

        // Aynı para biriminden olmalı
        if (!senderAccount.getCurrency().equals(receiverAccount.getCurrency())) {
            throw new RuntimeException("Currency mismatch: Can only transfer between accounts with the same currency.");
        }

        // Yeterli bakiye var mı kontrolü
        if (senderAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in sender's account.");
        }

        // Gönderici hesaptan para düş, alıcıya ekle
        accountServiceClient.decreaseBalance(senderAccountId, amount);
        accountServiceClient.increaseBalance(receiverAccountId, amount);

        // İşlemi kaydet
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

    // ATM'den para yatırma işlemi (sadece TRY yatırılabilir)
    public TransactionDto deposit(Long accountId, Double amount) {
        AccountDto account = accountServiceClient.getAccountById(accountId);

        // ATM'ye sadece TRY yatırılabilir, USD hesaplarında çeviri yapılmalı
        if (account.getCurrency().equals("USD")) {
            // TRY to USD çevirimi
            Double exchangedAmount = currencyExchangeService.convert("TRY", "USD", amount);
            accountServiceClient.increaseBalance(accountId, exchangedAmount);  // Hesaba dönüştürülmüş USD miktarını ekle
        } else if (account.getCurrency().equals("TRY")) {
            accountServiceClient.increaseBalance(accountId, amount);  // TRY olarak ekle
        } else {
            throw new RuntimeException("Invalid currency for deposit.");
        }

        // İşlemi kaydet
        Transaction transaction = new Transaction();
        transaction.setReceiverAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setCurrency("TRY");
        transaction.setTransactionDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    // ATM'den para çekme işlemi (sadece TRY çekilebilir)
    public TransactionDto withdraw(Long accountId, Double amount) {
        AccountDto account = accountServiceClient.getAccountById(accountId);

        // ATM'den sadece TRY çekilebilir
        if (!account.getCurrency().equals("TRY")) {
            throw new RuntimeException("ATM withdrawals are only allowed in TRY.");
        }

        // Yeterli bakiye kontrolü
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for withdrawal.");
        }

        // Bakiyeyi düş
        accountServiceClient.decreaseBalance(accountId, amount);

        // İşlemi kaydet
        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(accountId);  // Çeken hesabı gönderici kabul edelim
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAWAL");
        transaction.setCurrency("TRY");
        transaction.setTransactionDate(LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToDto(savedTransaction);
    }

    // Entity -> DTO dönüşümü
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

