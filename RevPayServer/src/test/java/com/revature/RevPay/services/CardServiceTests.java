package com.revature.RevPay.services;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.CardRepository;
import com.revature.RevPay.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CardServiceTests {
    @Autowired
    public CardService cardService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CardRepository cardRepository;
    @MockBean
    private UserService userService;

    //create card
    @Test
    void givenValidCardInformationWhenCreateCardReturnSuccess(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "11/25","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Success";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //missing card number
    @Test
    void givenMissingCardNumberWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card(null, "Kyle", "11/25","111",true,user);
        //shouldn't need these as it should fail first
        //        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
//        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
//        when(cardRepository.save(card)).thenReturn(card);
        String expected = "MissingInformation";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //duplicate card number for same user
    @Test
    void givenDuplicateCardInformationForSameUserWhenCreateCardReturnAlreadyExists(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", "11/23","987",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card2);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        String expected = "Card already exists for this user";

        String result = cardService.createCard(card1);

        Assertions.assertEquals(expected,result);

    }

    //missing expiration
    @Test
    void givenMissingCardExpirationWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", null,"111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Missing information";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //missing name
    @Test
    void givenMissingCardNameWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", null, "3/26","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Missing information";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //missing cvv
    @Test
    void givenMissingCardCVVWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "4/25",null,true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Missing information";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //missing isDebit
    @Test
    void givenMissingCardIsDebitWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "1/27","111",null,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Missing information";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //missing user
    @Test
    void givenMissingCardUserWhenCreateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "7/25","111",true,null);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "Missing information";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //no such user
    @Test
    void givenInvalidUserWhenCreateCardReturnNoMatchingUser(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "1/27","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(null);
        when(cardRepository.save(card)).thenReturn(card);
        String expected = "No matching user";

        String result = cardService.createCard(card);

        Assertions.assertEquals(expected,result);
    }
    //find card by number valid
    @Test
    void givenValidCardWhenFindCardByNumberReturnCard(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "1/27","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(card);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        Card result = cardService.findCardByCardNumber(card.getCardNumber());

        Assertions.assertEquals(card,result);
    }
    //find card by number invalid
    @Test
    void givenValidCardWhenFindCardByNumberReturnNull(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "1/27","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        Card result = cardService.findCardByCardNumber(card.getCardNumber());

        Assertions.assertNull(result);
    }
    //find cards by username valid
    @Test
    void givenValidUserWithCardsWhenFindCardByUsernameReturnListOfCard(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        Card card2 = new Card("22222", "Kyle", "1/27","221",true,user);
        List<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        when(cardRepository.getCardsByUser(user)).thenReturn(list);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        List<Card> result = cardService.findCardsByUsername(user.getUsername());

        Assertions.assertEquals(list,result);
    }
    //find cards by username valid no cards
    @Test
    void givenValidUserWithNoCardsWhenFindCardByUsernameReturnEmptyList(){
        User user = new User("test","1111","email","password");
        User searchUser = new User("searcher","1111","search","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        List<Card> list = new ArrayList<>();
        when(cardRepository.getCardsByUser(searchUser)).thenReturn(list);
        when(userService.getUserByUsername(searchUser.getUsername())).thenReturn(searchUser);

        List<Card> result = cardService.findCardsByUsername(searchUser.getUsername());

        Assertions.assertEquals(list,result);
    }
    //find cards by username invalid
    @Test
    void givenInvalidUserWithCardsWhenFindCardByUsernameReturnEmptyList(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        List<Card> list = new ArrayList<>();
        when(cardRepository.getCardsByUser(user)).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(null);

        List<Card> result = cardService.findCardsByUsername(user.getUsername());

        Assertions.assertEquals(list,result);
    }
    //find cards by user valid
    @Test
    void givenValidUserWithCardsWhenFindCardByUserReturnListOfCard(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        Card card2 = new Card("22222", "Kyle", "1/27","221",true,user);
        List<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        when(cardRepository.getCardsByUser(user)).thenReturn(list);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        List<Card> result = cardService.findCardsByUser(user);

        Assertions.assertEquals(list,result);
    }
    //find cards by user valid no cards
    @Test
    void givenValidUserWithNoCardsWhenFindCardByUserReturnEmptyList(){
        User user = new User("test","1111","email","password");
        User searchUser = new User("searcher","1111","search","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        List<Card> list = new ArrayList<>();
        when(cardRepository.getCardsByUser(searchUser)).thenReturn(list);
        when(userService.getUserByUsername(searchUser.getUsername())).thenReturn(searchUser);

        List<Card> result = cardService.findCardsByUser(searchUser);

        Assertions.assertEquals(list,result);
    }
    //find cards by user invalid
    @Test
    void givenInvalidUserWithCardsWhenFindCardByUserReturnEmptyList(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "1/27","111",true,user);
        List<Card> list = new ArrayList<>();
        when(cardRepository.getCardsByUser(user)).thenReturn(null);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(null);

        List<Card> result = cardService.findCardsByUser(user);

        Assertions.assertEquals(list,result);
    }
    //update card by number
    @Test
    void givenValidCardInformationWhenUpdateCardReturnSuccess(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", "11/27","222",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Success";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //update card given wrong number
    @Test
    void givenInvalidCardNumberWhenUpdateCardReturnNoExistingCard(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("22222", "Kyle", "11/27","222",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "No card exists";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing card number
    @Test
    void givenMissingCardNumberWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card(null, "Kyle", "11/27","222",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing expiration
    @Test
    void givenMissingCardExpirationWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", null,"222",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing name
    @Test
    void givenMissingCardNameWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", null, "11/27","222",true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing cvv
    @Test
    void givenMissingCardCVVWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", "11/27",null,true,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing isDebit
    @Test
    void givenMissingCardIsDebitWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", "11/27","222",null,user);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //missing user
    @Test
    void givenMissingCardUserWhenUpdateCardReturnMissingInformation(){
        User user = new User("test","1111","email","password");
        Card card1 = new Card("11111", "Kyle", "11/25","111",true,user);
        Card card2 = new Card("11111", "Kyle", "11/27","222",true,null);
        when(cardRepository.getByCardNumber(card1.getCardNumber())).thenReturn(card1);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(cardRepository.save(card1)).thenReturn(card1);
        when(cardRepository.save(card2)).thenReturn(card2);
        String expected = "Missing information";

        String result = cardService.updateCardByNumber(card1.getCardNumber(),card2);

        Assertions.assertEquals(expected,result);
    }
    //delete card by number valid
    @Test
    void givenValidCardNumberWhenDeleteCardByNumberThenReturnCard(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "11/25","111",true,user);
        when(cardRepository.getByCardNumber(card.getCardNumber())).thenReturn(card);
        when(cardRepository.deleteByCardNumber(card.getCardNumber())).thenReturn(card);

        Card result = cardService.deleteCardByCardNumber(card.getCardNumber());

        Assertions.assertEquals(card,result);
    }
    //delete card by number invalid
    @Test
    void givenInvalidCardNumberWhenDeleteCardByNumberThenReturnNull(){
        User user = new User("test","1111","email","password");
        Card card = new Card("11111", "Kyle", "11/25","111",true,user);
        when(cardRepository.getByCardNumber("55555")).thenReturn(null);
        when(cardRepository.deleteByCardNumber(card.getCardNumber())).thenReturn(card);

        Card result = cardService.deleteCardByCardNumber("55555");

        Assertions.assertNull(result);
    }

}
