package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,String> {

    public Card getByCardNumber(String cardNumber);

    public List<Card> getCardsByUser(User user);
    public Card deleteByCardNumber(String cardNumber);
}
