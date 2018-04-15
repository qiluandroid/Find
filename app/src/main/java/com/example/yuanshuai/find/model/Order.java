package com.example.yuanshuai.find.model;

import java.sql.Timestamp;

/**
 * Created by yuanshuai on 2018/4/14.
 */

public class Order {
    private String id;
    private Timestamp time;
    private int amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    private Order.Type type;

    private String commentId;
    public enum Type
    {
        CHARGE,
        CHARGE_SUCCEEDED,
        TRANSFER,
        TRANSFER_SUCCEEDED,
        TRANSFER_FAILED,
        PAY,
        REWARD,
    }
}
