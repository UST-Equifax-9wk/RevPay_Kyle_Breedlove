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
    private Double cost;

    @Column
    private Boolean payWithBalance;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User payee;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Card card;

    public Transaction() {
    }

    public Transaction(Integer transactionID, LocalDateTime date, Double cost, Boolean payWithBalance, User payee, User payer, Card card) {
        this.transactionID = transactionID;
        this.date = date;
        this.cost = cost;
        this.payWithBalance = payWithBalance;
        this.payee = payee;
        this.payer = payer;
        this.card = card;
    }

    public Transaction(LocalDateTime date, Double cost, Boolean payWithBalance, User payee, User payer, Card card) {
        this.date = date;
        this.cost = cost;
        this.payWithBalance = payWithBalance;
        this.payee = payee;
        this.payer = payer;
        this.card = card;
    }

    public Transaction(LocalDateTime date, Double cost, User payee, User payer) {
        this.date = date;
        this.cost = cost;
        this.payWithBalance = true;
        this.payee = payee;
        this.payer = payer;
    }

    public Transaction(LocalDateTime date, Double cost, User payee, User payer, Card card) {
        this.date = date;
        this.cost = cost;
        this.payWithBalance = false;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getPayWithBalance() {
        return payWithBalance;
    }

    public void setPayWithBalance(Boolean payWithBalance) {
        this.payWithBalance = payWithBalance;
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
        return Objects.equals(getTransactionID(), that.getTransactionID()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getCost(), that.getCost()) && Objects.equals(getPayWithBalance(), that.getPayWithBalance()) && Objects.equals(getPayee(), that.getPayee()) && Objects.equals(getPayer(), that.getPayer()) && Objects.equals(getCard(), that.getCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionID(), getDate(), getCost(), getPayWithBalance(), getPayee(), getPayer(), getCard());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", date=" + date +
                ", cost=" + cost +
                ", payWithBalance=" + payWithBalance +
                ", payee=" + payee +
                ", payer=" + payer +
                ", card=" + card +
                '}';
    }
}
