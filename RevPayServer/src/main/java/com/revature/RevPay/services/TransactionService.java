package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static com.revature.RevPay.RevPayApplication.logger;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class TransactionService {
    private TransactionRepository transactionRepository;
    private UserService userService;
    private CardService cardService;
    private LoanService loanService;
    private EmailService emailService;
    @Autowired
    public TransactionService(EmailService emailService, LoanService loanService, TransactionRepository transactionRepository, CardService cardService, UserService userService){
        this.transactionRepository=transactionRepository;
        this.userService=userService;
        this.cardService=cardService;
        this.loanService=loanService;
        this.emailService=emailService;
    }
    public String createTransaction(Transaction transaction){
        if(transaction.getPayee()==null || transaction.getPayer()==null||transaction.getCost()==null||transaction.getPayWithBalance()==null)return "Missing information";
        if(!transaction.getPayWithBalance()&&transaction.getCard()==null)return "Missing card";
        if(transaction.getPayWithBalance() && transaction.getPayer().getBalance()<transaction.getCost()) return "Insufficient funds in balance";
        if(userService.getUserByUsername(transaction.getPayee().getUsername())==null || userService.getUserByUsername(transaction.getPayer().getUsername())==null)return"Invalid user";
        if(!transaction.getPayWithBalance() && cardService.findCardByCardNumber(transaction.getCard().getCardNumber())==null)return "Invalid card";
        if(transaction.getCard().getCardNumber().isEmpty())transaction.setCard(null);
        //add funds to payees balance
        if(!transaction.getPayee().getUsername().equals(transaction.getPayer().getUsername())) {
            userService.updateBalance(transaction.getPayee().getUsername(), transaction.getPayee().getBalance() + (double) transaction.getCost());
            emailService.transactionEmail(transaction);

        }
        else{
            Loan loan =loanService.getLoanByUsername(transaction.getPayer().getUsername());
            if(loan==null) return "No loan to pay";
            if(loan.getBalance()<transaction.getCost())return "Cannot pay more than remaining balance";
            String result = loanService.updateBalance(loan.getLoanID(),transaction.getCost());
            if(!result.equals("Success")) return "Something went wrong with loan payment: "+result;
            emailService.loanPaymentEmail(transaction);

        }
        //remove funds from payers balance if paying with balance
        if (transaction.getPayWithBalance())userService.updateBalance(transaction.getPayer().getUsername(), transaction.getPayer().getBalance() - (double) transaction.getCost());
        transactionRepository.save(transaction);
        logger.info("NEW TRANSACTION CREATED BETWEEN "+transaction.getPayee().getUsername()+" and "+transaction.getPayer().getUsername());
        return "Success";
    }
    public Transaction getTransactionById(Integer id){
        if(id==null)return null;
        return transactionRepository.getReferenceById(id);
    }
    public List<Transaction> getTransactionByUserAndDate(User user, LocalDateTime date){
        if(user==null || date==null)return null;
        return transactionRepository.getByPayeeOrPayerAndDate(user.getUsername(), date);
    }
    public List<Transaction> getTransactionByPayeeAndPayer(User payee, User payer){
        if(payee==null || payer==null)return null;
        return transactionRepository.getByPayeeAndPayer(payee.getUsername(), payer.getUsername());
    }
    public List<Transaction> getTransactionByUsername(String username){
        if(username==null)return null;
        return transactionRepository.getByPayeeOrPayer(username);
    }
    public List<Transaction> getTransactionByPayee(User payee){
        if(payee==null)return null;
        return transactionRepository.getByPayee(payee);
    }
    public List<Transaction> getTransactionByPayer(User payer){
        if(payer==null)return null;
        return transactionRepository.getByPayee(payer);
    }
    public Transaction deleteTransaction(Integer id){
        Transaction target = getTransactionById(id);
        if(id==null || target==null) return null;
        transactionRepository.deleteById(id);
        return target;
    }
}
