package com.example.yuanshuai.find.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yuanshuai on 2018/4/13.
 */

public class MissionComment {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public List<MissionCommentReply> getReplies() {
        return replies;
    }

    public void setReplies(List<MissionCommentReply> replies) {
        this.replies = replies;
    }

    private String comment;
    private String time;
    private UserInfoOutput user;

    List<MissionCommentReply> replies;
}
