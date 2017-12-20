package com.example.yuanshuai.find.activity;

import java.math.BigDecimal;

public class Ticket {
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getHas() {
        return has;
    }

    public void setHas(Integer has) {
        this.has = has;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String  getPrice() {
        return price;
    }

    public void setPrice(String  price) {
        this.price = price;
    }

    public String  getDistance() {
        return distance;
    }

    public void setDistance(String  distance) {
        this.distance = distance;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    private Integer tid;
    private Integer stid;


    private Integer counts;
    private Integer has=0;
    private String starttime;
    private String endtime;
    private String  price;
    private String  distance;
    private String tname;
}
