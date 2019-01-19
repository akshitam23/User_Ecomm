package com.example.User_Ecomm.Controller;

import com.example.User_Ecomm.DTO.AddressDTO;
import com.example.User_Ecomm.DTO.UserDTO;
import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Service.Impl.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody AddressDTO addressDTO) {
        System.out.println("Add Department method called");
        Address address=new Address();
        BeanUtils.copyProperties(addressDTO, address);
        addressService.add(address);

    }
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public Address select(@PathVariable("id") String id) {
       Address address = addressService.get(id);

        return address;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody UserDTO userDTO) {
        Address address=new Address();
        BeanUtils.copyProperties(userDTO, address);
       addressService.update(address);

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")String id) {
        addressService.delete(id);

    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Address> findAll() {
        List<Address> addresses=addressService.findAll();
        return addresses;
    }
}
