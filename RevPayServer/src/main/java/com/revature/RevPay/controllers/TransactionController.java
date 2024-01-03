package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.services.TransactionService;
import com.revature.RevPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TransactionController {
     private TransactionService transactionService;

     @Autowired
     public TransactionController(TransactionService transactionService){
         this.transactionService=transactionService;
     }

     @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction (@RequestBody Transaction transaction){
         transaction.setTransactionID(null);
         String result = this.transactionService.createTransaction(transaction);
         System.out.println(result);
         if(result.equals("Success"))return new ResponseEntity<>(transaction, HttpStatus.OK);
         else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

     @GetMapping("/transactions/{username}")
    public ResponseEntity<List<Transaction>> getTransactionsByUsername(@PathVariable String username){
         List<Transaction> result = this.transactionService.getTransactionByUsername(username);
         return new ResponseEntity<>(result,HttpStatus.OK);

     }
}
