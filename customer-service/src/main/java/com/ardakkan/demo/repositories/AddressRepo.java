package com.ardakkan.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ardakkan.demo.entities.Address;

public interface AddressRepo
extends JpaRepository<Address, Long> {

}
