package com.ardakkan.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ardakkan.demo.dtos.DepositRequestDto;
import com.ardakkan.demo.dtos.TransactionDto;
import com.ardakkan.demo.dtos.TransferRequestDto;
import com.ardakkan.demo.dtos.WithdrawRequestDto;
import com.ardakkan.demo.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transfer(@RequestBody TransferRequestDto transferRequest) {
        TransactionDto transaction = transactionService.transfer(
                transferRequest.getSenderAccountId(), 
                transferRequest.getReceiverAccountId(), 
                transferRequest.getAmount()
        );
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> deposit(@RequestBody DepositRequestDto depositRequest) {
        TransactionDto transaction = transactionService.deposit(
                depositRequest.getAccountId(),
                depositRequest.getAmount()
        );
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@RequestBody WithdrawRequestDto withdrawRequest) {
        TransactionDto transaction = transactionService.withdraw(
                withdrawRequest.getAccountId(),
                withdrawRequest.getAmount()
        );
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}

