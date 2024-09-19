package com.ardakkan.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ardakkan.demo.dtos.AddressDto;
import com.ardakkan.demo.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Yeni adres oluşturma
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto createdAddress = addressService.createAddress(addressDto);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED); // 201 Created
    }

    // Tüm adresleri listeleme
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK); // 200 OK
    }

    // ID ile adres bulma
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        Optional<AddressDto> addressDto = addressService.getAddressById(id);
        return addressDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)) // 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
    }

    // Adres güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        try {
            AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Adres silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}

