package com.example.User_Ecomm.Service.Impl;

import com.example.User_Ecomm.utilities.HashingPassword;
import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class UserServicesImpl implements UserService {
      ;

        @Autowired
        private UserRepository userRepository;

        HashingPassword hashingPassword=new HashingPassword();
        @Override
        public void add(Users user) {
            userRepository.save(user);
        }

        @Override
        @Transactional(readOnly = true)
        public Users get(String userId) {
            return userRepository.findOne(userId);
        }

        @Override
        public void update(Users users) {
            userRepository.save(users);
        }

        @Override
        public void delete(String userId) {
            userRepository.delete(userId);
        }

        @Override
        public List<Address> getAllAddress(String userId) {
                return userRepository.findOne(userId).getAddress();
        }
        @Override
        public String login(String email, String password,Users users) {
                Users pass=userRepository.findByEmail(email);
                if(pass==null)
                        return "User Not Found";
                else{
                if(pass.getPassword().equals(users.getPassword()))
                        return pass.getUserId();
                else
                        return "Password dont match";}

        }

}
