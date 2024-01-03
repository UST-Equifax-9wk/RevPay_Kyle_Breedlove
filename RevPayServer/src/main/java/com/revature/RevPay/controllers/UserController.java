package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.Login;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User user){
        String result = userService.createUser(user);

        if(result.equals("Success")) return new ResponseEntity<>(user,HttpStatus.CREATED);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/username/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User result = userService.getUserByUsername(username);
        if(result==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("/users/email/{email}")
    ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User result = userService.getUserByEmail(email);
        if(result==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("/users/phone-number/{phoneNumber}")
    ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber){
        User result = userService.getUserByPhone(phoneNumber);
        if(result==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PostMapping("/login")
    ResponseEntity<User> login(@RequestBody Login login, HttpServletResponse response){
        boolean result = userService.userLogin(login.getUsername(),login.getPassword());
        if(result){
            Cookie cookie = new Cookie("username",login.getUsername());
            //one hour cookie
            cookie.setMaxAge(60*60);
            //any path in application
            cookie.setPath("/");
            response.addCookie(cookie);
            return new ResponseEntity<>(userService.getUserByUsername(login.getUsername()),HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    ResponseEntity<User> register(@RequestBody User user){
        String result = userService.createUser(user);
        if(result.equals("Success")) return new ResponseEntity<>(user,HttpStatus.CREATED);
        else if(result.equals("Username already exists")||result.equals("Email already in use")) return new ResponseEntity<>(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/change-password")
    ResponseEntity<User> changePassword(@RequestBody User user){
        if(user==null||user.getUsername()==null||user.getPassword()==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String result = userService.updatePassword(user.getUsername(),user.getPassword());
        if(result.equals("Success")) return new ResponseEntity<>(user,HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @GetMapping("/user/delete/{username}")
//    ResponseEntity<User> deleteUser(@PathVariable String username){
//        return new ResponseEntity<>(userService.deleteUserByUsername(username),HttpStatus.OK);
//    }


}
