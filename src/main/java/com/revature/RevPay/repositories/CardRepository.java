package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,String> {

    public Card getByCardNumber(String cardNumber);
}
