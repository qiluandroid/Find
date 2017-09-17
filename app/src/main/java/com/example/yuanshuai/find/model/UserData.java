package com.example.yuanshuai.find.model;

public class UserData {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public int getGifted() {
        return gifted;
    }

    public void setGifted(int gifted) {
        this.gifted = gifted;
    }

    private int integral;
    private int gift;
    private int gifted;
}
