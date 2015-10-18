/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neo.web.auth;

import com.neo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserController {

    @Autowired
    private UserService userService = null;

    public UserController() {

    }

    
    public void doAction(){
        Long count = userService.getUserCount();
        System.out.println("Count of users " + count);
    }
        

  

}
