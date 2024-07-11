package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address saveAddress(Address address);
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Integer id);
    Address updateAddress(Integer id, Address address);
    void deleteAddress(Integer id);
}

