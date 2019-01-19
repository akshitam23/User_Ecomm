package com.example.User_Ecomm.Service.Impl;

import com.example.User_Ecomm.Entity.Address;

import java.util.List;

public interface AddressService {
    void add(Address address);
   Address get(String addressId);
    void update(Address address);
    void delete(String addressId);
    public List<Address> findAll();
}
