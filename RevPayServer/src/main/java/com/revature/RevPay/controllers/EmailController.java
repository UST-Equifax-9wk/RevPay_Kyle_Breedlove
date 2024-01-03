package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.Business;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.services.EmailService;
import com.revature.RevPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmailController {
    private EmailService emailService;
    private UserService userService;

    @Autowired
    EmailController(EmailService emailService, UserService userService){
        this.emailService=emailService;
        this.userService=userService;
    }

    @PostMapping("/email/request-business")
    public ResponseEntity<HttpStatus> requestBusinessAccount(@RequestBody Business business){
        emailService.requestBusinessAccount(business);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/email/request-payment/{username}/{amount}")
    public ResponseEntity<HttpStatus> requestPayment(@RequestBody User user, @PathVariable String username, @PathVariable String amount){
        User requester=userService.getUserByUsername(username);
        String result = emailService.requestPayment(requester,user,amount);
        if(result.equals("No user found"))return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if(result.equals("Success"))return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/email/request-loan/{amount}")
    public ResponseEntity<HttpStatus> requestLoan(@RequestBody User user, @PathVariable String amount){
        emailService.requestBusinessLoan(user, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
