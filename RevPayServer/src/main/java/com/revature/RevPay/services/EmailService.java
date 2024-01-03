package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Business;
import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.revature.RevPay.RevPayApplication.logger;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String adminEmail;
    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository){
        this.javaMailSender=javaMailSender;
        User admin = userRepository.getByUsername("testUser");
        this.adminEmail=admin.getEmail();
    }

    public void sendEmail(String target, String subject, String message){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(target);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(this.adminEmail);
        javaMailSender.send(email);
        logger.info("EMAIL SENT TO "+target+" in regards to: "+subject);

    }

    public void transactionEmail(Transaction transaction){
        sendEmail(
                transaction.getPayer().getEmail(),
                "Payment Confirmation From RevPay",
                transaction.getPayer().getUsername() + " payed " +transaction.getPayee().getUsername() + " $"+transaction.getCost()
                );
        sendEmail(
                transaction.getPayee().getEmail(),
                "Payment Received From RevPay User",
                transaction.getPayer().getUsername() + " payed " +transaction.getPayee().getUsername() + " $"+transaction.getCost()
        );
    }
    public void loanPaymentEmail(Transaction transaction){
        sendEmail(
                transaction.getPayee().getEmail(),
                "Loan Payment Confirmation",
                "Thank you for paying $"+transaction.getCost()+" towards your loan."
        );
    }
    public void createAccountEmail(User user){
        sendEmail(user.getEmail(), "RevPay Account Created","Thank you for creating an account with RevPay");
    }

    public void changePasswordEmail(User user){
        sendEmail(user.getEmail(), "RevPay Password Changed", "Your RevPay password has been updated");
    }
    public void loanCreatedEmail(Loan loan){
        sendEmail(loan.getUser().getEmail(), "RevPay Loan Confirmation", "Your request for a RevPay loan has been accepted");
    }

    public void requestBusinessAccount(Business business){
        sendEmail(adminEmail, "Business Account Request",
                "User: "+business.getUser().getUsername()+" is requesting an account upgrade to a business account with "+business.getBusinessName()+" at "+business.getBusinessAddress()+".");

    }
    public void requestBusinessLoan(User user, String amount){
        sendEmail(adminEmail, "Business Loan Request",
                "User: "+user.getUsername()+" is requesting a business loan for $"+amount+".");

    }
    public String requestPayment(User requester, User requestee, String amount){

        if(requestee==null) return "No user found";
        sendEmail(requestee.getEmail(), "RevPay Payment Request",
                "User: "+requester.getUsername()+" is requesting you to pay $"+amount);
        return "Success";
    }

}
