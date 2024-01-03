package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.revature.RevPay.RevPayApplication.logger;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class LoanService {
    private LoanRepository loanRepository;
    private UserService userService;
    private static final double defaultInterest=1.05;
    private static final double defaultMinimumPayment=25.00;
    private static final Integer defaultPaymentDue=1;
    private static final double latePayemntFee=10;
    private EmailService emailService;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserService userService, EmailService emailService){
        this.loanRepository=loanRepository;
        this.userService=userService;
        this.emailService=emailService;
    }

    public String createLoan(Loan loan){
        if(loan==null || loan.getUser()==null || loan.getBalance()==null) return "Missing information";
        if(userService.getUserByUsername(loan.getUser().getUsername())==null) return "No user";
        if(loan.getCreationDate()==null) loan.setCreationDate(LocalDateTime.now());
        if(loan.getInterestRate()==null) loan.setInterestRate(defaultInterest);
        if(loan.getMinimumPayment()==null) loan.setMinimumPayment(defaultMinimumPayment);
        if(loan.getPaymentDueDay()==null) loan.setPaymentDueDay(defaultPaymentDue);
        loan.setLastInterestUpdate(LocalDateTime.now());
        loan.setLastPaymentDate(LocalDateTime.now());
        loanRepository.save(loan);
        emailService.loanCreatedEmail(loan);
        logger.info("NEW LOAN CREATED FOR "+loan.getUser().getUsername()+" FOR $"+loan.getBalance());
        return "Success";

    }

    public Loan getLoanById(Integer id){
        if(id==null)return null;
        return loanRepository.getReferenceById(id);
    }
    public Loan getLoanByUser(User user){
        if(user==null)return null;
        return getLoanByUsername(user.getUsername());
    }
    public Loan getLoanByUsername(String username){
        if(username==null)return null;
        User user = userService.getUserByUsername(username);
        return loanRepository.getByUser(user);
    }
    public Double getBalance(Integer id){
        if(id==null)return null;
        return getLoanById(id).getBalance();
    }
    public String updateBalance(Integer id, Double amountToChangeBy){
        if(id==null || amountToChangeBy==null) return "Missing information";
        Loan loan = getLoanById(id);
        if(loan==null) return"No loan";
        if(loan.getBalance()<=0)return "No balance to pay";
        if(loan.getBalance()<amountToChangeBy)return"Cannot pay more than current balance";
        if(loan.getBalance()<loan.getMinimumPayment()){
            loan.setBalance(loan.getBalance()-amountToChangeBy);
            loan.setLastPaymentDate(LocalDateTime.now());
        }
        else{
            if(amountToChangeBy<loan.getMinimumPayment())return"Must pay more than minimum payment";
            loan.setBalance(loan.getBalance()-amountToChangeBy);
            loan.setLastPaymentDate(LocalDateTime.now());
        }

        return "Success";
    }

    public Loan deleteLoanByID(Integer id){
        if(id==null)return null;
        Loan loan = getLoanById(id);
        if(loan==null) return null;
        return loanRepository.deleteLoanByLoanID(id);
    }
    public Loan deleteLoanByUser(User user){
        if(user==null)return null;
        Loan loan = getLoanByUser(user);
        if(loan==null)return null;
        return deleteLoanByID(loan.getLoanID());
    }

    public String runAdminChecks(User admin){
        if(admin==null || !admin.isAdmin())return"Not an admin";

        List<Loan> loans =loanRepository.getAll();
        LocalDateTime now = LocalDateTime.now();

        for(Loan loan:loans){
            //if it has been 1 month or more since interest was added, add interest and update date
            if(ChronoUnit.MONTHS.between(loan.getLastInterestUpdate(),now)>=1){
                loan.setBalance(loan.getBalance()*loan.getInterestRate());
                loan.setLastInterestUpdate(now);
            }
            //if there has not been a payment before payment due, charge late fee
            if(ChronoUnit.MONTHS.between(loan.getLastPaymentDate(),now)>1 && now.getDayOfMonth()>loan.getPaymentDueDay()){
                loan.setBalance(loan.getBalance()+latePayemntFee);
                loan.setLastPaymentDate(now);
            }
        }

        return "Success";
    }
}
