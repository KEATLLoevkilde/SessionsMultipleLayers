package com.example.sessionsmultiplelayers.models;

public class User {
    private int userID;
    private String userName;
    private String password;


    public User() {}

    public User(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public int getUserID() { return userID; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }
    public void setUserID(int userID) { this.userID = userID; }
}
