package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class LoanRepositoryTests {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    void insertNewLoan(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        Assertions.assertEquals(loan,entityManager.find(Loan.class,loan.getLoanID()));
    }
    @Test
    void getValidLoanById(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        Loan result = loanRepository.getReferenceById(loan.getLoanID());
        Assertions.assertEquals(loan,result);
    }
    @Test
    void getInvalidLoanById(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        Optional<Loan> result = loanRepository.findById(15452410);
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void getValidLoanByUser(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        Loan result = loanRepository.getByUser(user);
        Assertions.assertEquals(loan,result);
    }
    @Test
    void getInvalidLoanByUser(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        User invalid = new User("ky","111","a","pass",0.0,true,true);
        Loan result = loanRepository.getByUser(invalid);
        Assertions.assertNull(result);
    }
    @Test
    void updateLoan(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        Loan update = new Loan(loan.getLoanID(),1485.0,0.07,LocalDateTime.now(),25.00, 1, LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(update);
        Assertions.assertEquals(update,entityManager.find(Loan.class,loan.getLoanID()));
    }
    @Test
    void removeLoan(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Loan loan = new Loan(2037.24, 0.07, LocalDateTime.now(),25.00,LocalDateTime.now(),LocalDateTime.now(),user);
        loanRepository.save(loan);
        loanRepository.deleteById(loan.getLoanID());
        Assertions.assertNull(entityManager.find(Loan.class,loan.getLoanID()));
    }
}
