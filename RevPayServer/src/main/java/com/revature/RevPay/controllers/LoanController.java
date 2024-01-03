package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.services.LoanService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoanController {
    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService){
        this.loanService=loanService;
    }

    @PostMapping("/loan")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan){
        System.out.println("In create loan");
        String result = loanService.createLoan(loan);
        System.out.println(result);
        if(result.equals("Success")) return new ResponseEntity<>(loanService.getLoanByUser(loan.getUser()), HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/loan/user/{username}")
    public ResponseEntity<Loan> getLoanByUsername(@PathVariable String username){
        Loan result = loanService.getLoanByUsername(username);
        if(result==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
    @GetMapping("/loan/admin/checks")
    public ResponseEntity<HttpStatus> checkLoansForUpdates(@RequestBody User admin){
        String result = loanService.runAdminChecks(admin);
        if(result.equals("Success"))return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    //admin endpoint to update interests

    //when pay loan, use transaction payee=payer,
    //check that balance is enough if not using card
}
