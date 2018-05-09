package com.example.yuanshuai.find.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yuanshuai on 2018/4/13.
 */

public class Mission {
    private String id;
    private String time;
    private double x;
    private double y;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public UserInfoOutput getUser() {
        return user;
    }

    public void setUser(UserInfoOutput user) {
        this.user = user;
    }

    public List<MissionComment> getComments() {
        return comments;
    }

    public void setComments(List<MissionComment> comments) {
        this.comments = comments;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private String address;
    private String title;
    private String description;
    private int price;
    private String type;
    private boolean finish;

    private UserInfoOutput user;
    private List<MissionComment> comments;
    private List<String> images;
}

