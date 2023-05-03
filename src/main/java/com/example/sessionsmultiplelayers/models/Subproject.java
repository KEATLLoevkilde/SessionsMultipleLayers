package com.example.sessionsmultiplelayers.models;

public class Subproject {
    private int subprojectID;
    private String name;
    private int projectID;

    public Subproject(int subprojectID, String name, int projectID) {
        this.subprojectID = subprojectID;
        this.name = name;
        this.projectID = projectID;
    }

    public Subproject() {
    }

    public int getSubprojectID() {
        return subprojectID;
    }

    public void setSubprojectID(int subprojectID) {
        this.subprojectID = subprojectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
