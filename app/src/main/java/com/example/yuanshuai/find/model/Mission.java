package com.example.yuanshuai.find.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yuanshuai on 2018/4/13.
 */

public class Mission {
    private String id;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public List<MissionImage> getImages() {
        return images;
    }

    public void setImages(List<MissionImage> images) {
        this.images = images;
    }

    public List<MissionComment> getComments() {
        return comments;
    }

    public void setComments(List<MissionComment> comments) {
        this.comments = comments;
    }

    private Timestamp time;

    private double x;
    private double y;

    private String address;


    private String title;
    private String description;

    private int price;
    private boolean finish = false;

    private List<MissionImage> images;

    private List<MissionComment> comments;
}

