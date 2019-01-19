package com.example.User_Ecomm.Repository;

import com.example.User_Ecomm.Entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}
