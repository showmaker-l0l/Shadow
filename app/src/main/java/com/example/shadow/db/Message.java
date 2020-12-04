package com.example.shadow.db;

import org.litepal.crud.LitePalSupport;

public class Message extends LitePalSupport {

    private String content;
    private int userID;
    private long time;
    private String date;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

}
