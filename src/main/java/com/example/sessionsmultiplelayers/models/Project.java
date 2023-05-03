package com.example.sessionsmultiplelayers.models;

public class Project {
    private int projectID;
    private String name;
    private int userID;

    public Project(int projectID, String name, int userID) {
        this.projectID = projectID;
        this.name = name;
        this.userID = userID;
    }

    public Project() {
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
