package com.example.User_Ecomm.Controller;

import com.example.User_Ecomm.DTO.AppDTO;
import com.example.User_Ecomm.DTO.UserDTO;
import com.example.User_Ecomm.DTO.WebDTO;
import com.example.User_Ecomm.Entity.App;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Entity.Web;
import com.example.User_Ecomm.Repository.AppRepository;
import com.example.User_Ecomm.Repository.WebRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {
@Autowired
private WebRepository webRepository;
@Autowired
private AppRepository appRepository;

    @RequestMapping(value = "WebToken/add/{id}", method = RequestMethod.POST)
    public String addweb(@PathVariable("id") String id) {
        System.out.println("Add token method called");
        Web web=new Web();
        String response;
         web.setUserId(id);
         try {
             webRepository.save(web);
             web=webRepository.findWebByUserId(id);
             response=web.getUserId();
         }
         catch(Exception e)
        {
            System.out.println(e.getMessage());
            response="you are already loogged in";
        }

        return response;

    }
    @RequestMapping(value = "AppToken/add/{id}", method = RequestMethod.POST)
    public String addapp(@PathVariable("id") String id) {
        System.out.println("Add Department method called");
        App app=new App();
        String response;
        app.setUserId(id);
        try {
            appRepository.save(app);
            app=appRepository.findAppByUserId(id);
            response=app.getUserId();
            System.out.println(response);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            response="you are already loogged in";
        }

        return response;
    }
    @RequestMapping(value = "/getwebtoken/{id}", method = RequestMethod.GET)
    public String select(@PathVariable("id") String id) {
      String token=null;
        try {
       Web web= webRepository.findWebByUserId(id);
        token=web.getToken();}
        catch(Exception e){

        }
        if(token!=null)

        return   token;
   else
       return "Unauthenticated";
    }
    @RequestMapping(value = "/getapptoken/{id}", method = RequestMethod.GET)
    public String selectq(@PathVariable("id") String id) {
        String token=null;
        try {
         App app= appRepository.findAppByUserId(id);
         token=app.getToken();
        }
        catch(Exception e){

        }
        if(token!=null)

            return   token;
        else
            return "Unauthenticated";
    }
}
