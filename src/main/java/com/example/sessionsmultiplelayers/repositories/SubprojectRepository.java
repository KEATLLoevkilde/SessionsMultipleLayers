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
public class SubprojectRepository {
    String sql = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void createSubproject(Subproject subproject){
        try{
            sql = "INSERT INTO subProjects (subproject_name, project_id) VALUE (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setInt(2, subproject.getProjectID());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
