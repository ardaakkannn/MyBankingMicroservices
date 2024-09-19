package com.ardakkan.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ardakkan.demo.entities.Customer;


public interface CustomerRepo
extends JpaRepository<Customer, Long> {

}
