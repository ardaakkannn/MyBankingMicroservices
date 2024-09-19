package com.ardakkan.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardakkan.demo.dtos.AccountDto;
import com.ardakkan.demo.dtos.AddressDto;
import com.ardakkan.demo.dtos.CustomerDto;
import com.ardakkan.demo.entities.Address;
import com.ardakkan.demo.entities.Customer;
import com.ardakkan.demo.repositories.CustomerRepo;

@Service
public class CustomerService {

	private final CustomerRepo customerRepo;
    private final AccountServiceClient accountServiceClient;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, AccountServiceClient accountServiceClient) {
        this.customerRepo = customerRepo;
        this.accountServiceClient = accountServiceClient;
    }

    // Müşteri ID ile müşteri ve hesaplarını bulma
    public Optional<CustomerDto> getCustomerWithAccountsById(Long customerId) {
    	
        Optional<Customer> customerOptional = customerRepo.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Account Service'ten müşteri hesaplarını al
            List<AccountDto> accounts = accountServiceClient.getAccountsByCustomerId(customerId);

            // Customer entity'sini DTO'ya çevir ve hesapları ekle
            CustomerDto customerDto = mapToDTO(customer);
            customerDto.setAccounts(accounts); // Hesapları DTO'ya ekle

            return Optional.of(customerDto);
        } else {
            return Optional.empty();
        }
    }
    
    // Yeni müşteri oluşturma (CustomerDTO döner)
    public CustomerDto createCustomer(CustomerDto customerDTO) {
        Customer customer = mapToEntity(customerDTO); // DTO -> Entity
        Customer savedCustomer = customerRepo.save(customer); // Veritabanına kaydet
        return mapToDTO(savedCustomer); // Entity -> DTO
    }

    // Tüm müşterileri listeleme (List<CustomerDTO> döner)
    public List<CustomerDto> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(this::mapToDTO) // Her bir Customer entity'sini DTO'ya çevirir
                .collect(Collectors.toList());
    }

    // ID ile müşteri bulma (CustomerDTO döner)
    public Optional<CustomerDto> getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        return customerOptional.map(this::mapToDTO); // Optional -> DTO
    }

    // Müşteriyi güncelleme (CustomerDTO döner)
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customer.setTcId(customerDto.getTcId());
            customer.setEmail(customerDto.getEmail());
            customer.setAddress(mapToAddressEntity(customerDto.getAddress())); // DTO -> Entity (Address)
            Customer updatedCustomer = customerRepo.save(customer); // Güncellenen müşteri kaydı
            return mapToDTO(updatedCustomer); // Güncellenen müşteri -> DTO
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    // Müşteri silme
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    // Entity -> DTO dönüşümü
    private CustomerDto mapToDTO(Customer customer) {
        AddressDto addressDTO = new AddressDto(
                customer.getAddress().getId(),
                customer.getAddress().getCountry(),
                customer.getAddress().getCity(),
                customer.getAddress().getDistrict(),
                customer.getAddress().getStreet(),
                customer.getAddress().getZipCode()
        );

        return new CustomerDto(
                customer.getId(),
                customer.getTcId(),
                addressDTO, // CustomerDTO içinde AddressDTO
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                new ArrayList<>()
        );
    }

    // DTO -> Entity dönüşümü
    private Customer mapToEntity(CustomerDto customerDTO) {
    	Address address = mapToAddressEntity(customerDTO.getAddress());

        return new Customer(
                customerDTO.getId(),
                customerDTO.getTcId(),
                address, // Customer içinde Address
                customerDTO.getName(),
                customerDTO.getSurname(),
                customerDTO.getEmail()
        );
    }
    
    private Address mapToAddressEntity(AddressDto addressDto) {
    	Address address = new Address(
    			addressDto.getCountry(),
    			addressDto.getCity(),
    			addressDto.getDistrict(),
    			addressDto.getStreet(),
    			addressDto.getZipCode()
        );
    	
    	return address;
    	
    	
    }
    

	
}
