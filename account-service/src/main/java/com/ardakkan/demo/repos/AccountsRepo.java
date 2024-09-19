package com.ardakkan.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ardakkan.demo.entity.Account;

public interface AccountsRepo
extends JpaRepository<Account, Long> {
	List<Account> findByCustomerId(Long customerId);
}
