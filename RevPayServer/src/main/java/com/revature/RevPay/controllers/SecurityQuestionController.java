package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.SecurityQuestion;
import com.revature.RevPay.services.SecurityQuestionService;
import com.revature.RevPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SecurityQuestionController {
    private SecurityQuestionService securityQuestionService;
    private UserService userService;

    @Autowired
    public SecurityQuestionController(SecurityQuestionService securityQuestionService, UserService userService){
        this.securityQuestionService=securityQuestionService;
        this.userService=userService;
    }

    @PostMapping("/security-question")
    public ResponseEntity<SecurityQuestion> createSecurityQuestion(@RequestBody SecurityQuestion securityQuestion){
        String result = securityQuestionService.createSecurityQuestion(securityQuestion);
        if(result.equals("Success"))return new ResponseEntity<>(securityQuestion, HttpStatus.OK);
        else{
            System.out.println(result);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/security-question/user/{username}")
    public ResponseEntity<List<SecurityQuestion>> getSecurityQuestionByUser(@PathVariable String username){
        List<SecurityQuestion> result = securityQuestionService.getSecurityQuestionsByUser(username);
        if(result.isEmpty())return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
