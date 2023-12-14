package com.revature.RevPay.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name="cards")
public class Card {

    @Id
    @Column(name="card_number")
    private String cardNumber;

    @Column(name="owner_name",nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String expiration;

    @Column(nullable = false)
    private String cvv;

    @Column(name="is_debit", nullable = false)
    private boolean isDebit;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    @JsonBackReference
    private User user;

    public Card() {
    }

    public Card(String cardNumber, String ownerName, String expiration, String cvv, boolean isDebit, User user) {
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiration = expiration;
        this.cvv = cvv;
        this.isDebit = isDebit;
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isDebit() {
        return isDebit;
    }

    public void setDebit(boolean debit) {
        isDebit = debit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return isDebit() == card.isDebit() && Objects.equals(getCardNumber(), card.getCardNumber()) && Objects.equals(getOwnerName(), card.getOwnerName()) && Objects.equals(getExpiration(), card.getExpiration()) && Objects.equals(getCvv(), card.getCvv()) && Objects.equals(getUser(), card.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getOwnerName(), getExpiration(), getCvv(), isDebit(), getUser());
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", expiration='" + expiration + '\'' +
                ", cvv='" + cvv + '\'' +
                ", isDebit=" + isDebit +
                ", user=" + user +
                '}';
    }
}
