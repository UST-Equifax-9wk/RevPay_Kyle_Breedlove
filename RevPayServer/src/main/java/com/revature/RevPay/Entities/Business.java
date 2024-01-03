package com.revature.RevPay.Entities;

public class Business {
    private String businessName;
    private String businessAddress;
    private User user;

    public Business(String businessName, String businessAddress, User user){
        this.businessAddress=businessAddress;
        this.businessName=businessName;
        this.user=user;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
