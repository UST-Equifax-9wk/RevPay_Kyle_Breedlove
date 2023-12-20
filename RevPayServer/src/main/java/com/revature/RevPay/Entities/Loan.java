package com.revature.RevPay.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @Column(name="loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanID;

    @Column(nullable = false)
    private double balance;

    @Column(name="interest_rate",nullable = false)
    private double interestRate;

    @Column(name="creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name="minimum_payment")
    @ColumnDefault("25.00")
    private double minimumPayment;

    //day of month payment is due
    @Column(name="payment_due_day")
    @ColumnDefault("1")
    private Integer paymentDueDay;

    @Column(name="last_interest_update")
    @CreationTimestamp
    private LocalDateTime lastInterestUpdate;

    @Column(name="last_payment_date")
    @CreationTimestamp
    private LocalDateTime lastPaymentDate;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    public Loan() {
    }

    public Loan(double balance, double interestRate, LocalDateTime creationDate, double minimumPayment, LocalDateTime lastInterestUpdate, LocalDateTime lastPaymentDate, User user) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creationDate = creationDate;
        this.minimumPayment = minimumPayment;
        this.lastInterestUpdate = lastInterestUpdate;
        this.lastPaymentDate = lastPaymentDate;
        this.user = user;
    }

    public Loan(double balance, double interestRate, LocalDateTime creationDate, double minimumPayment, Integer paymentDueDay, LocalDateTime lastInterestUpdate, LocalDateTime lastPaymentDate, User user) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.creationDate = creationDate;
        this.minimumPayment = minimumPayment;
        this.paymentDueDay = paymentDueDay;
        this.lastInterestUpdate = lastInterestUpdate;
        this.lastPaymentDate = lastPaymentDate;
        this.user = user;
    }

    public Loan(Integer loanID, double balance, double interestRate, LocalDateTime creationDate, double minimumPayment, Integer paymentDueDay, LocalDateTime lastInterestUpdate, LocalDateTime lastPaymentDate, User user) {
        this.loanID = loanID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.creationDate = creationDate;
        this.minimumPayment = minimumPayment;
        this.paymentDueDay = paymentDueDay;
        this.lastInterestUpdate = lastInterestUpdate;
        this.lastPaymentDate = lastPaymentDate;
        this.user = user;
    }

    public Integer getLoanID() {
        return loanID;
    }

    public void setLoanID(Integer loanID) {
        this.loanID = loanID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public Integer getPaymentDueDay() {
        return paymentDueDay;
    }

    public void setPaymentDueDay(Integer paymentDueDay) {
        this.paymentDueDay = paymentDueDay;
    }

    public LocalDateTime getLastInterestUpdate() {
        return lastInterestUpdate;
    }

    public void setLastInterestUpdate(LocalDateTime lastInterestUpdate) {
        this.lastInterestUpdate = lastInterestUpdate;
    }

    public LocalDateTime getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
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
        if (!(o instanceof Loan loan)) return false;
        return Double.compare(getBalance(), loan.getBalance()) == 0 && Double.compare(getInterestRate(), loan.getInterestRate()) == 0 && Double.compare(getMinimumPayment(), loan.getMinimumPayment()) == 0 && Objects.equals(getLoanID(), loan.getLoanID()) && Objects.equals(getCreationDate(), loan.getCreationDate()) && Objects.equals(getPaymentDueDay(), loan.getPaymentDueDay()) && Objects.equals(getLastInterestUpdate(), loan.getLastInterestUpdate()) && Objects.equals(getLastPaymentDate(), loan.getLastPaymentDate()) && Objects.equals(getUser(), loan.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoanID(), getBalance(), getInterestRate(), getCreationDate(), getMinimumPayment(), getPaymentDueDay(), getLastInterestUpdate(), getLastPaymentDate(), getUser());
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanID=" + loanID +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", creationDate=" + creationDate +
                ", minimumPayment=" + minimumPayment +
                ", paymentDueDay=" + paymentDueDay +
                ", lastInterestUpdate=" + lastInterestUpdate +
                ", lastPaymentDate=" + lastPaymentDate +
                ", user=" + user +
                '}';
    }
}
