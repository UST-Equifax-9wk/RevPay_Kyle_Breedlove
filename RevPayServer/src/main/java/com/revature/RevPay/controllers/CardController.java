package com.revature.RevPay.controllers;

import com.revature.RevPay.Entities.Card;
import com.revature.RevPay.Entities.User;
import com.revature.RevPay.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService=cardService;
    }

    @PostMapping("/cards")
    ResponseEntity<Card> createCard(@RequestBody Card card){
        String result = cardService.createCard(card);

        if(result.equals("Success")) return new ResponseEntity<>(card, HttpStatus.CREATED);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //find card by number

    //find card by user
    @GetMapping("/cards/user/{username}")
    ResponseEntity<List<Card>> getCardsByUser(@PathVariable String username){
        List<Card> result = cardService.findCardsByUsername(username);
        if(!result.isEmpty()) return new ResponseEntity<>(result, HttpStatus.OK);

        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    //delete card

    //update card


}
