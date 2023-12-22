package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.User;
import com.revature.RevPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @CrossOrigin
    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User user){
        String result = userService.createUser(user);

        if(result.equals("Success")) return new ResponseEntity<>(user,HttpStatus.CREATED);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping("/users/username/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User result = userService.getUserByUsername(username);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/login/{username}/{password}")
    ResponseEntity<User> login(@PathVariable String username, @PathVariable String password){
        boolean result = userService.userLogin(username,password);
        if(result)return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
