package com.example.yuanshuai.find.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yuanshuai on 2018/4/13.
 */

public class MissionComment {
    private String id;

    private User user;


    private Mission mission;


    private Timestamp time;


    private String comment;


    private List<MissionCommentReply> replies;
}
