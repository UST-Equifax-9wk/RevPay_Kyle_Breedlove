package com.revature.RevPay.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity(name="users")
public class User {
    private static final int minimumPasswordLength = 8;
    private static final int minimumUsernameLength = 4;
    @Id
    @Column
    private String username;

    @Column(name="phone_number", unique = true,nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    @ColumnDefault("0.00")
    private Double balance;

    @Column(name="admin")
    @ColumnDefault("false")
    private Boolean admin;

    @Column(name="business")
    @ColumnDefault("false")
    private Boolean business;

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
//        this.balance = 0.0;
//        this.isAdmin = false;
//        this.isBusiness = false;
    }

    public User(String username, String phoneNumber, String email, String password, Double balance, Boolean admin, Boolean business) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.admin = admin;
        this.business = business;
    }
    public static int getMinimumPasswordLength(){
        return minimumPasswordLength;
    }
    public static int getMinimumUsernameLength(){
        return minimumUsernameLength;
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

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean isBusiness() {
        return business;
    }

    public void setBusiness(Boolean business) {
        this.business = business;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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
                ", isAdmin=" + admin +
                ", isBusiness=" + business +
                '}';
    }
}
