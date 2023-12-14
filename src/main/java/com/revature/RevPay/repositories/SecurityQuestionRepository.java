package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.SecurityQuestion;
import com.revature.RevPay.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion,Integer> {
    SecurityQuestion findByQuestionID(Integer i);

    List<SecurityQuestion> findByUser(User user);
}
