package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.model.Address;
import com.backend.EcommerceProjectBackEnd.repository.AddressRepository;
import com.backend.EcommerceProjectBackEnd.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {

    final private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address updateAddress(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreetAddress(address.getStreetAddress());
            existingAddress.setCity(address.getCity());
            existingAddress.setState(address.getState());
            existingAddress.setZip(address.getZip());
            return addressRepository.save(existingAddress);
        } else {
            throw new RuntimeException("Address not found with id " + id);
        }
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}

