package com.revature.RevPay.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @Column(name="transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionID;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime date;

    @Column(nullable = false)
    private double cost;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User payee;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Card card;

    public Transaction() {
    }

    public Transaction(LocalDateTime date, double cost, User payee, User payer, Card card) {
        this.date = date;
        this.cost = cost;
        this.payee = payee;
        this.payer = payer;
        this.card = card;
    }

    public Transaction(Integer transactionID, LocalDateTime date, double cost, User payee, User payer, Card card) {
        this.transactionID = transactionID;
        this.date = date;
        this.cost = cost;
        this.payee = payee;
        this.payer = payer;
        this.card = card;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Double.compare(getCost(), that.getCost()) == 0 && Objects.equals(getTransactionID(), that.getTransactionID()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getPayee(), that.getPayee()) && Objects.equals(getPayer(), that.getPayer()) && Objects.equals(getCard(), that.getCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionID(), getDate(), getCost(), getPayee(), getPayer(), getCard());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", date=" + date +
                ", cost=" + cost +
                ", payee=" + payee +
                ", payer=" + payer +
                ", card='" + card + '\'' +
                '}';
    }
}
