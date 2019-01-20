package com.example.User_Ecomm.Service.Impl;

import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;

import java.util.List;

public interface UserService {
    void add(Users user);
    Users get(String userId);
    void update(Users user);
    void delete(String userId);
    List<Address> getAllAddress(String userId);
    String login(String email,String password,Users user);


}
