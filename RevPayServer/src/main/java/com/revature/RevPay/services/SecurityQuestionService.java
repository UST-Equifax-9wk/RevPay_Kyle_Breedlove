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
        if(sq.getSecurityQuestion()==null || sq.getSecurityAnswer()==null || sq.getUser()==null)return"Missing information";
        if(userService.getUserByUsername(sq.getUser().getUsername())==null) return "No such user";
        if(getSecurityQuestionByQuestionAndUser(sq.getSecurityQuestion(),sq.getUser())!=null)return "Question already exists for this user";
        securityQuestionRepository.save(sq);
        return "Success";
    }

    public SecurityQuestion getSecurityQuestionByID(Integer id){
        if(id==null)return null;
        return securityQuestionRepository.findByQuestionID(id);
    }
    public List<SecurityQuestion> getSecurityQuestionsByUser(String username){
        if(username==null || userService.getUserByUsername(username)==null) return null;
        return securityQuestionRepository.findByUser(userService.getUserByUsername(username));
    }

    public SecurityQuestion getSecurityQuestionByQuestionAndUser(String question, User user){
        if(question==null || user==null || user.getUsername()==null) return null;
        List<SecurityQuestion> list = securityQuestionRepository.findByUser(user);
        for (SecurityQuestion securityQuestion : list) {
            if (securityQuestion.getSecurityQuestion().equalsIgnoreCase(question)) return securityQuestion;
        }
        return null;
    }

    public String updateSecurityQuestionByQuestionAndUser(String question, User user, SecurityQuestion sq){
        SecurityQuestion target = getSecurityQuestionByQuestionAndUser(question,user);
        return updateSecurityQuestionByID(target.getQuestionID(), sq);
    }

    public String updateSecurityQuestionByID(Integer id, SecurityQuestion sq){
        SecurityQuestion original = getSecurityQuestionByID(id);
        if(id==null || sq==null || sq.getUser()==null || original==null)return "Missing information";
        if(original.getUser()!=sq.getUser())return"Invalid user";
        if(sq.getSecurityQuestion()==null)sq.setSecurityQuestion(original.getSecurityQuestion());
        if(sq.getSecurityAnswer()==null)sq.setSecurityAnswer(original.getSecurityAnswer());
        sq.setQuestionID(id);
        securityQuestionRepository.save(sq);
        return "Success";
    }
}
