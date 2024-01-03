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

import static com.revature.RevPay.RevPayApplication.logger;

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
        if(
                card.getCardNumber()==null || card.isDebit()==null || card.getCvv()==null ||
                card.getExpiration()==null || card.getOwnerName()==null || card.getUser()==null
        ) return "Missing information";
        if(userService.getUserByUsername(card.getUser().getUsername())==null) return "No existing user";
        if(findCardByCardNumber(card.getCardNumber())!=null)return "Duplicate card number";

        cardRepository.save(card);
        logger.info("CARD CREATED FOR "+card.getUser().getUsername());
        return "Success";
    }

    public Card findCardByCardNumber(String number){
        if(number==null)return null;
        return cardRepository.getByCardNumber(number);
    }

    public List<Card> findCardsByUsername(String username){

        User user = userService.getUserByUsername(username);
        if(user==null)return null;

        return findCardsByUser(user);
    }

    public List<Card> findCardsByUser(User user){
        if(userService.getUserByUsername(user.getUsername())==null)return null;
        return cardRepository.getCardsByUser(user);
    }
    public String updateCardByNumber(String number, Card newCard){
        Card original = findCardByCardNumber(number);
        if(original==null)return "No card exists";
        if(newCard.getCardNumber()==null)return"Missing information";
        if(newCard.isDebit()==null)newCard.setDebit(original.isDebit());
        if(newCard.isDebit()!=original.isDebit())return"Cannot change card types";
        if(newCard.getCardNumber()==null)newCard.setCardNumber(number);
        if(!newCard.getCardNumber().equals(original.getCardNumber()))return "No card exists";
        if(newCard.getOwnerName()==null)newCard.setOwnerName(original.getOwnerName());
        if(newCard.getCvv()==null)newCard.setCvv(original.getCvv());
        if(newCard.getUser()==null)newCard.setUser(original.getUser());
        if(userService.getUserByUsername(newCard.getUser().getUsername())==null) return "Invalid user";
        cardRepository.save(newCard);
        return "Success";
    }

    public Card deleteCardByCardNumber(String number){
        if(findCardByCardNumber(number)==null)return null;
        return cardRepository.deleteByCardNumber(number);
    }


}
