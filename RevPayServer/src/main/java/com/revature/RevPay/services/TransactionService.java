package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class TransactionService {
    private TransactionRepository transactionRepository;
    private UserService userService;
    private CardService cardService;

    public String createTransaction(Transaction transaction){
        return null;
    }
    public String updateTransaction(Integer id){
        return null;
    }
    public Transaction getTransactionById(Integer id){
        return null;
    }
    public List<Transaction> getTransactionByUserAndDate(User user, LocalDateTime date){
        return null;
    }
    public List<Transaction> getTransactionByPayeeAndPayer(User payee, User payer){
        return null;
    }
    public List<Transaction> getTransactionByUser(User user){
        return null;
    }
    public List<Transaction> getTransactionByPayee(User payee){
        return null;
    }
    public List<Transaction> getTransactionByPayer(User payer){
        return null;
    }
    public Transaction deleteTransaction(Integer id){
        return null;
    }
}
