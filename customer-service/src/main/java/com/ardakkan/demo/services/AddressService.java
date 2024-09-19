package com.ardakkan.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ardakkan.demo.dtos.AddressDto;
import com.ardakkan.demo.entities.Address;
import com.ardakkan.demo.repositories.AddressRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    // Yeni adres oluşturma (AddressDto döner)
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = mapToEntity(addressDto); // DTO -> Entity
        Address savedAddress = addressRepo.save(address); // Veritabanına kaydet
        return mapToDTO(savedAddress); // Entity -> DTO
    }

    // Tüm adresleri listeleme (List<AddressDto> döner)
    public List<AddressDto> getAllAddresses() {
        return addressRepo.findAll()
                .stream()
                .map(this::mapToDTO) // Her bir Address entity'sini DTO'ya çevirir
                .collect(Collectors.toList());
    }

    // ID ile adres bulma (AddressDto döner)
    public Optional<AddressDto> getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepo.findById(id);
        return addressOptional.map(this::mapToDTO); // Optional -> DTO
    }

    // Adresi güncelleme (AddressDto döner)
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        Optional<Address> addressOptional = addressRepo.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setCountry(addressDto.getCountry());
            address.setCity(addressDto.getCity());
            address.setDistrict(addressDto.getDistrict());
            address.setStreet(addressDto.getStreet());
            address.setZipCode(addressDto.getZipCode());
            Address updatedAddress = addressRepo.save(address); // Güncellenen adres kaydı
            return mapToDTO(updatedAddress); // Güncellenen adres -> DTO
        } else {
            throw new RuntimeException("Address not found with id: " + id);
        }
    }

    // Adresi silme
    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }

    // Entity -> DTO dönüşümü
    private AddressDto mapToDTO(Address address) {
        return new AddressDto(
                address.getId(),
                address.getCountry(),
                address.getCity(),
                address.getDistrict(),
                address.getStreet(),
                address.getZipCode()
        );
    }

    // DTO -> Entity dönüşümü
    private Address mapToEntity(AddressDto addressDto) {
        return new Address(
                addressDto.getCountry(),
                addressDto.getCity(),
                addressDto.getDistrict(),
                addressDto.getStreet(),
                addressDto.getZipCode()
        );
    }
}

