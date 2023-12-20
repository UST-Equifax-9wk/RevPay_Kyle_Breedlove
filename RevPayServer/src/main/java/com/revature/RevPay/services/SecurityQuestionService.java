package com.revature.RevPay.services;

import com.revature.RevPay.Entities.SecurityQuestion;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.SecurityQuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityQuestionService {

    private SecurityQuestionRepository securityQuestionRepository;
    private UserService userService;

    @Autowired
    public SecurityQuestionService(SecurityQuestionRepository securityQuestionRepository,UserService userService){
        this.securityQuestionRepository=securityQuestionRepository;
        this.userService=userService;
    }

    public String createSecurityQuestion(SecurityQuestion sq){
        return null;
    }

    public SecurityQuestion getSecurityQuestionByID(Integer id){
        return null;
    }
    public List<SecurityQuestion> getSecurityQuestionsByUser(User user){
        return null;
    }

    public SecurityQuestion getSecurityQuestionByQuestionAndUser(String question, User user){
        return null;
    }

    public String updateSecurityQuestionByQuestionAndUser(String question, User user){
        return null;
    }

    public String updateSecurityQuestionByID(Integer id){
        return null;
    }
}
