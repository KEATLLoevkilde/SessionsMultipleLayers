package com.example.sessionsmultiplelayers.repositories;

import com.example.sessionsmultiplelayers.DB_Connector;
import com.example.sessionsmultiplelayers.models.Project;
import com.example.sessionsmultiplelayers.models.Subproject;
import com.example.sessionsmultiplelayers.models.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {
    String sql = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void createProject(Project project){
        try{
            sql = "INSERT INTO projects (project_name, user_id) VALUE (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Project> getProjects(User user) {
        try {
            sql = "SELECT project_id, project_name FROM projects WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUserID());
            resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                projects.add(new Project(resultSet.getInt("project_id"), resultSet.getString("project_name"), user.getUserID()));
            }
            return projects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Project getProjectByID(int projectID) {
        try {
            sql = "SELECT project_name, user_id FROM projects WHERE project_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            resultSet = preparedStatement.executeQuery();
            Project project = null;
            if (resultSet.next()) {
                project = new Project(projectID, resultSet.getString("project_name"), resultSet.getInt("user_id"));
            }
            return project;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Subproject> getSubprojects(Project project) {
        try {
            sql = "SELECT subproject_id, subproject_name FROM subprojects WHERE project_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, project.getProjectID());
            resultSet = preparedStatement.executeQuery();
            List<Subproject> subprojects = new ArrayList<>();
            while (resultSet.next()) {
                subprojects.add(new Subproject(resultSet.getInt("subproject_id"), resultSet.getString("subproject_name"), project.getProjectID()));
            }
            return subprojects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
