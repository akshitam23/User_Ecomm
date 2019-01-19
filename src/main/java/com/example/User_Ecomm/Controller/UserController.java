package com.example.User_Ecomm.Controller;

import com.example.User_Ecomm.DTO.UserDTO;
import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Service.Impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody UserDTO userDTO) {
        System.out.println("Add Department method called");
        Users user = new Users();
        BeanUtils.copyProperties(userDTO, user);
        userService.add(user);

    }

        @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
        public Users select(@PathVariable("id") String id) {
            System.out.println(id);
            Users users = userService.get(id);
            System.out.println(users.toString());
            return users;
        }

        @RequestMapping(value = "/update", method = RequestMethod.PUT)
        public void update(@RequestBody UserDTO userDTO) {
            Users users = new Users();
            BeanUtils.copyProperties(userDTO, users);
            userService.update(users);

        }

        @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
        public void delete(@PathVariable("id")String id) {
            userService.delete(id);

        }
    @RequestMapping(value = "/getAllAdress/{id}", method = RequestMethod.GET)
    public List<Address> getAllAddress(@PathVariable("id")String id) {

        List<Address> addresses=userService.getAllAddress(id);
        return addresses;


    }

}
