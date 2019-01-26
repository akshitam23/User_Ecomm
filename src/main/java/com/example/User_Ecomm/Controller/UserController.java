package com.example.User_Ecomm.Controller;

import com.example.User_Ecomm.DTO.UserDTO;
import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Repository.AppRepository;
import com.example.User_Ecomm.Repository.UserRepository;
import com.example.User_Ecomm.Repository.WebRepository;
import com.example.User_Ecomm.Service.Impl.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebRepository webRepository;
    @Autowired
    private AppRepository appRepository;
@CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UserDTO userDTO) {
        System.out.println("Add user method called");
        Users user = new Users();
    System.out.println(userDTO.toString());
        BeanUtils.copyProperties(userDTO, user);
        if (userRepository.findByEmail(userDTO.getEmail()) == null) {
            userService.add(user);
            String postUrl="http://localhost:8080/cart/add";
            RestTemplate restTemplate=new RestTemplate();
            ResponseEntity<String> postResponse=restTemplate.postForEntity(postUrl,user,String.class);
            return "User added";
        } else
            return "Email Already Exists";

    }

    @RequestMapping(value = "/login/app", method = RequestMethod.POST)
    public ResponseEntity<String> loginapp(@RequestBody UserDTO userDTO) {
        ResponseEntity<String> appResponse = null;
        Users user = new Users();
        BeanUtils.copyProperties(userDTO, user);
      String appid=  userService.login(userDTO.getEmail(), userDTO.getPassword(), user);
        if(!appid.equals("User Not Found") || !appid.equals("Password dont match"))
       {
        String url="http://localhost:8000/token/AppToken/add/"+appid;
        RestTemplate restTemplate=new RestTemplate();
      appResponse=restTemplate.postForEntity(url,null,String.class);}
        return appResponse;
    }
    @RequestMapping(value = "/login/web", method = RequestMethod.POST)
    public ResponseEntity<String> loginweb(@RequestBody UserDTO userDTO) {
        ResponseEntity<String> postResponse = null;
        Users user = new Users();
        BeanUtils.copyProperties(userDTO, user);
      String webid= userService.login(userDTO.getEmail(), userDTO.getPassword(), user);
      if(!webid.equals("User Not Found") || !webid.equals("Password dont match")){
          String webUrl="http://localhost:8000/token/WebToken/add/"+webid;
        RestTemplate restTemplate=new RestTemplate();
       postResponse =restTemplate.postForEntity(webUrl,null,String.class);}
      return  postResponse;
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
    public void delete(@PathVariable("id") String id) {
        userService.delete(id);

    }

    @Transactional
    @RequestMapping(value = "/logout/web/{id}", method = RequestMethod.DELETE)
    public void logoutweb(@PathVariable("id") String id) {
       webRepository.logout(id);

    }
@Transactional
    @RequestMapping(value = "/logout/app/{id}", method = RequestMethod.DELETE)
    public void deleteapp(@PathVariable("id") String id) {
       appRepository.logout(id);

    }

    @RequestMapping(value = "/getAllAdress/{id}", method = RequestMethod.GET)
    public List<Address> getAllAddress(@PathVariable("id") String id) {

        List<Address> addresses = userService.getAllAddress(id);
        return addresses;

    }


}
