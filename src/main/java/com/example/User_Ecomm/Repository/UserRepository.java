package com.example.User_Ecomm.Repository;

import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface UserRepository extends CrudRepository<Users, String> {

       // List<Address> findAdressByUserId(@Param("userId") String userId);
    Users findByEmail(String email);

    }

