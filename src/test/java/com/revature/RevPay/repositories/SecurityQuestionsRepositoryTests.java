package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.SecurityQuestion;
import com.revature.RevPay.Entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class SecurityQuestionsRepositoryTests {
    @Autowired
    SecurityQuestionRepository securityQuestionRepository;
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Test
    void insertSecurityQuestion(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        SecurityQuestion sq = new SecurityQuestion(1,"question","answer",user);
        securityQuestionRepository.save(sq);
        Assertions.assertEquals(sq,entityManager.find(SecurityQuestion.class,1));
    }
    @Test
    void findValidSecurityQuestionThroughQuestionID(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        SecurityQuestion sq = new SecurityQuestion("question","answer",user);
        securityQuestionRepository.save(sq);
        SecurityQuestion result = securityQuestionRepository.findByQuestionID(sq.getQuestionID());
        Assertions.assertEquals(sq,result);
    }
    @Test
    void findValidSecurityQuestionsThroughUser(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        List<SecurityQuestion> given = new ArrayList<>();
        SecurityQuestion sq = new SecurityQuestion(1,"question","answer",user);
        securityQuestionRepository.save(sq);
        given.add(sq);
        SecurityQuestion sq2 = new SecurityQuestion("q2","a2",user);
        securityQuestionRepository.save(sq2);
        given.add(sq2);
        List<SecurityQuestion> result = securityQuestionRepository.findByUser(user);
        Assertions.assertEquals(given,result);
    }

    @Test
    void findInvalidSecurityQuestionByID(){
        SecurityQuestion sq = securityQuestionRepository.findByQuestionID(1);
        Assertions.assertNull(sq);
    }
    @Test
    void findInvalidSecurityQuestionByUser(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        List<SecurityQuestion> sq = securityQuestionRepository.findByUser(user);
        Assertions.assertTrue(sq.isEmpty());
    }

    @Test
    void updateSecurityQuestion(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        SecurityQuestion sq = new SecurityQuestion("question","answer",user);
        securityQuestionRepository.save(sq);
        SecurityQuestion newQuestion = new SecurityQuestion(sq.getQuestionID(),"new","same",user);
        securityQuestionRepository.save(newQuestion);
        Assertions.assertEquals(newQuestion,entityManager.find(SecurityQuestion.class,sq.getQuestionID()));
    }
    @Test
    void removeSecurityQuestion(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        SecurityQuestion sq = new SecurityQuestion("question","answer",user);
        securityQuestionRepository.save(sq);
        securityQuestionRepository.deleteById(sq.getQuestionID());
        Assertions.assertNull(entityManager.find(SecurityQuestion.class,sq.getQuestionID()));
    }
}
