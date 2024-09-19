package com.ardakkan.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ardakkan.demo.entities.Transaction;



public interface TransactionRepository
extends JpaRepository<Transaction, Long> {

}
