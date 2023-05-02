package com.example.sessionsmultiplelayers.repositories;

import com.example.sessionsmultiplelayers.DB_Connector;
import com.example.sessionsmultiplelayers.models.Project;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
