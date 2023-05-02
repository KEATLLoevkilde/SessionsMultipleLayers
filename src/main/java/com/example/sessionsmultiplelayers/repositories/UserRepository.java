package com.example.sessionsmultiplelayers.repositories;

import com.example.sessionsmultiplelayers.DB_Connector;
import com.example.sessionsmultiplelayers.models.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    String sql = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

//    public void createUser(User user){
//        try{
//            sql = "INSERT INTO users (user_name) VALUE (?)";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.executeUpdate();
//
//            sql = "SELECT user_id FROM users WHERE user_name = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, user.getName());
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                user.setUserID(resultSet.getInt("user_id"));
//            }
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
    public void createUser(User user) {
        try {
            sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            sql = "SELECT user_id FROM users WHERE user_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user.setUserID(resultSet.getInt("user_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String userName, String password) {
        try {
            sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User( resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("user_password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
