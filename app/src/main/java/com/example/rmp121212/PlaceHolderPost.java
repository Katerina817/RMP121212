package com.example.rmp121212;

public class PlaceHolderPost {
    private int userID;
    private int id;
    private String title;
    private String body;
    public int getUserId() {
        return userID;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public void setUserId(int userID) {
        this.userID=userID;
    }
    public void setId(int id) {
        this.id=id;
    }
    public void setTitle(String title) {
        this.title=title;
    }
    public void setBody(String body) {
        this.body=body;
    }
}