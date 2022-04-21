package com.stackroute.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Read {
    public Map<String, String> readOperation(int id){
    	 Map<String, String> readResponse = new HashMap<>();
        //Load the required drivers
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Try to create a connection with your database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?useSSL=false", "root", "root1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee where id=?");) {
        	preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
           
            while (resultSet.next()) {
            	readResponse.put("id", resultSet.getInt(1)+"");
            	readResponse.put("name", resultSet.getString(2));
            	readResponse.put("age",resultSet.getInt(3)+"");
            	readResponse.put("gender",resultSet.getString(4));
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return readResponse;
    }
}
