package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class CardService {
    private CardRepository cardRepository;
    private UserService userService;

    @Autowired
    CardService(CardRepository cardRepository, UserService userService) {
        this.cardRepository=cardRepository;
        this.userService=userService;
    }

    public String createCard(Card card){
        return null;
    }

    public Card findCardByCardNumber(String number){
        return null;
    }

    public List<Card> findCardsByUsername(String username){
        List<Card>result=new ArrayList<>();
        return result;
    }

    public List<Card> findCardsByUser(User user){
        if(userService.getUserByUsername(user.getUsername())==null)return null;
        return findCardsByUsername(user.getUsername());
    }
    public String updateCardByNumber(String number, Card newCard){
        return null;
    }

    public Card deleteCardByCardNumber(String number){
        return null;
    }
    public Card deleteCardByCard(Card card){
        if(card==null || card.getCardNumber()==null)return null;
        return deleteCardByCardNumber(card.getCardNumber());
    }

}
