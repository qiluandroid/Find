package com.example.yuanshuai.find.model;

import java.sql.Timestamp;

/**
 * Created by yuanshuai on 2018/4/13.
 */

public class MissionCommentReply {

    private String id;
    private String reply;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserInfoOutput getUser() {
        return user;
    }

    public void setUser(UserInfoOutput user) {
        this.user = user;
    }

    private String time;

    private UserInfoOutput user;
}
