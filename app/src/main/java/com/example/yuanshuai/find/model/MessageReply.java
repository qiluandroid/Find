package com.example.yuanshuai.find.model;

import java.sql.Timestamp;

/**
 * Created by yuanshuai on 2018/4/14.
 */

public class MessageReply {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    private String reply;
    private String userId;
    private Timestamp time;

}
