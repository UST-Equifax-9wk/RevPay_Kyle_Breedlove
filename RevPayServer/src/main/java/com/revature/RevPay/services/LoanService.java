package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class LoanService {
    private LoanRepository loanRepository;
    private UserService userService;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserService userService){
        this.loanRepository=loanRepository;
        this.userService=userService;
    }

    public String createLoan(Loan loan){
        return null;
    }

    public Loan getLoanById(Integer id){
        return null;
    }
    public Loan getLoanByUser(User user){
        return null;
    }
    public Double getBalance(Integer id){
        return null;
    }
    public String updateBalance(Integer id, double amountToChangeBy){
        return null;
    }

    public Loan deleteLoanByID(Integer id){
        return null;
    }
    public Loan deleteLoanByUser(User user){
        return null;
    }
}
