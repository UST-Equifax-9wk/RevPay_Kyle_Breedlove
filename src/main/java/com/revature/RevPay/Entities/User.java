package com.revature.RevPay.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.Objects;

@Entity(name="users")
public class User {

    @Id
    @Column
    private String username;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    @ColumnDefault("0")
    private Integer balance;

    @Column(name="is_admin")
    @ColumnDefault("false")
    private boolean isAdmin;

    @Column(name="is_Business")
    @ColumnDefault("false")
    private boolean isBusiness;

    //TODO do I need this?
//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
//    private List<Card> cards;
    public User() {
    }

    public User(String username, String phoneNumber, String email, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(String username, String phoneNumber, String email, String password, Integer balance, boolean isAdmin, boolean isBusiness) {
        this(username, phoneNumber, email, password);
        this.balance = balance;
        this.isAdmin = isAdmin;
        this.isBusiness = isBusiness;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isAdmin() == user.isAdmin() && isBusiness() == user.isBusiness() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getBalance(), user.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPhoneNumber(), getEmail(), getPassword(), getBalance(), isAdmin(), isBusiness());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isAdmin=" + isAdmin +
                ", isBusiness=" + isBusiness +
                '}';
    }
}
