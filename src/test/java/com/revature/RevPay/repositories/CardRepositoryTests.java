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
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Assertions.assertEquals(card,entityManager.find(Card.class,card.getCardNumber()));
    }

    @Test
    void getValidCard(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Card result = cardRepository.getReferenceById(card.getCardNumber());
        Assertions.assertEquals(card,result);
    }
    @Test
    void getInvalidCard(){
        Card card = cardRepository.getByCardNumber("idDoesntMatter");
        Assertions.assertNull(card);
    }
    @Test
    void updateCard(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        Card updated = new Card("111111111", "Kyle B", "11/27","222",false,user);
        cardRepository.save(updated);
        Assertions.assertEquals(updated,entityManager.find(Card.class,card.getCardNumber()));
    }
    @Test
    void removeCard(){
        User user = new User("kylebreedlove","111","email","pass",0,true,true);
        userRepository.save(user);
        Card card = new Card("111111111", "Kyle Breedlove", "11/25","111",true,user);
        cardRepository.save(card);
        cardRepository.delete(card);
        Assertions.assertNull(entityManager.find(Card.class,card.getCardNumber()));
    }


}
