package com.ardakkan.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ardakkan.demo.services.CustomerService;
import com.ardakkan.demo.dtos.CustomerDto;




@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    
    //Finding both customer and accounts by Customer ID
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<CustomerDto> getCustomerWithAccountsById(@PathVariable Long customerId) {
        Optional<CustomerDto> customerDtoOptional = customerService.getCustomerWithAccountsById(customerId);

        if (customerDtoOptional.isPresent()) {
            return new ResponseEntity<>(customerDtoOptional.get(), HttpStatus.OK);  // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }

    // Creating new customer
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED); // 201 Created
    }

    // Listing all Customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK); // 200 OK
    }

    // Finding Customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        Optional<CustomerDto> customerDto = customerService.getCustomerById(id);
        return customerDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)) // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
    }

    // Updating Customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        try {
            CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Deleting Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

}
