package com.example.User_Ecomm.Service.Impl;

import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public class AddressServicesImpl implements AddressService {


        @Autowired
        private AddressRepository addressRepository;

        @Override
        public void add(Address address) {
            addressRepository.save(address);
        }
        @Override
        @Transactional(readOnly = true)
        public Address get(String addressId) {
            return addressRepository.findOne(addressId);
        }

        @Override
        public void update(Address address) {
            addressRepository.save(address);
        }

        @Override
        public void delete(String userId) {
            addressRepository.delete(userId);
        }
        @Override
        public List<Address> findAll() {
            List<Address> alist = new ArrayList<>();
            Iterable<Address> aiterable=addressRepository.findAll();
            for (Address address: aiterable) {
                alist.add(address);
            }
            return alist;

        }

    }
