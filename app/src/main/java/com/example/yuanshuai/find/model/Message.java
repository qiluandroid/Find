package com.example.yuanshuai.find.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yuanshuai on 2018/4/14.
 */

public class Message {
    private String id;
    private UserInfoOutput fromUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserInfoOutput getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserInfoOutput fromUser) {
        this.fromUser = fromUser;
    }

    public UserInfoOutput getToUser() {
        return toUser;
    }

    public void setToUser(UserInfoOutput toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<MessageReply> getReplies() {
        return replies;
    }

    public void setReplies(List<MessageReply> replies) {
        this.replies = replies;
    }

    private UserInfoOutput toUser;
    private String message;
    private String  time="1525355544690";
    private List<MessageReply> replies;
}
