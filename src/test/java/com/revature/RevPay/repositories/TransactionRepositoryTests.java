package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TransactionRepositoryTests {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    void insertTransaction(){
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        Assertions.assertEquals(transaction,entityManager.find(Transaction.class,transaction.getTransactionID()));
    }
    @Test
    void getTransactionByID(){
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        Transaction result = transactionRepository.getReferenceById(transaction.getTransactionID());
        Assertions.assertEquals(transaction,result);
    }
    @Test
    void getValidTransactionByPayee(){
        List<Transaction> list = new ArrayList<Transaction>();
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        list.add(transaction);
        List<Transaction> result = transactionRepository.getByPayee(transaction.getPayee());
        Assertions.assertEquals(list,result);
    }
    @Test
    void getInvalidTransactionByPayee(){
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        List<Transaction> result = transactionRepository.getByPayee(transaction.getPayer());
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void getValidTransactionByPayer(){
        List<Transaction> list = new ArrayList<Transaction>();
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        list.add(transaction);
        List<Transaction> result = transactionRepository.getByPayer(transaction.getPayer());
        Assertions.assertEquals(list,result);
    }
    @Test
    void getInvalidTransactionByPayer(){
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        List<Transaction> result = transactionRepository.getByPayer(transaction.getPayee());
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void getValidTransactionByAnyInvolvement(){
        List<Transaction> list = new ArrayList<Transaction>();
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        list.add(transaction);
        Transaction second = new Transaction(LocalDateTime.now(), 18.99, payer, payee, card);
        transactionRepository.save(second);
        list.add(second);
        List<Transaction> result = transactionRepository.getByPayeeOrPayer(payee.getUsername());
        Assertions.assertEquals(list,result);
    }
    @Test
    void getInvalidTransactionByAnyInvolvement(){
        List<Transaction> list = new ArrayList<Transaction>();
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        User neither = new User("neither","111","n","pass",0,true,true);
        userRepository.save(neither);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        list.add(transaction);
        List<Transaction> result = transactionRepository.getByPayeeOrPayer(neither.getUsername());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void removeTransactionByID(){
        User payee = new User("payee","111","email","pass",0,true,true);
        userRepository.save(payee);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,payee);
        cardRepository.save(card);
        User payer = new User("payer","111","a","pass",0,true,true);
        userRepository.save(payer);
        Transaction transaction = new Transaction(LocalDateTime.now(),12.25,payee,payer,card);
        transactionRepository.save(transaction);
        transactionRepository.deleteById(transaction.getTransactionID());
        Assertions.assertNull(entityManager.find(Transaction.class,transaction.getTransactionID()));
    }
}
