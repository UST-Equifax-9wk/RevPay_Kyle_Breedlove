package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
public class CardRepositoryTests {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    void insertValidCard(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Assertions.assertEquals(card,entityManager.find(Card.class,card.getCardNumber()));
    }

    @Test
    void getValidCardById(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Card result = cardRepository.getReferenceById(card.getCardNumber());
        Assertions.assertEquals(card,result);
    }

    @Test
    void getInvalidCardById(){
        Card card = cardRepository.getByCardNumber("idDoesntMatter");
        Assertions.assertNull(card);
    }
    @Test
    void getValidCardsByUsername(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Card card1 = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        Card card2 = new Card("22222", "Kyle Breedlove", "12/25","222",true,user);
        List<Card> list = new ArrayList<>();
        cardRepository.save(card1);
        list.add(card1);
        cardRepository.save(card2);
        list.add(card2);
        List<Card> result = cardRepository.getCardsByUser(user);
        Assertions.assertEquals(list,result);
    }
    @Test
    void getNoCardsByValidUsername(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        List<Card> result = cardRepository.getCardsByUser(user);
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void updateCard(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Card updated = new Card("111111111", "Kyle B", "11/27","222",false,user);
        cardRepository.save(updated);
        Assertions.assertEquals(updated,entityManager.find(Card.class,card.getCardNumber()));
    }
    @Test
    void removeCard(){
        User user = new User("kylebreedlove","111","email","pass",0.0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        cardRepository.delete(card);
        Assertions.assertNull(entityManager.find(Card.class,card.getCardNumber()));
    }


}
