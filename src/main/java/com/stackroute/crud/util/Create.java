package com.stackroute.crud.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Create {

    public Integer createOperation(String name, int age, String gender){

        //Load the required drivers
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date updatedDate = new Date(calendar.getTimeInMillis());
        
        //Try to create a connection with your database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?useSSL=false", "root", "root1234");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee(name,age,gender,updated_time) VALUES (?,?,?,?)")){
        	
        	preparedStatement.setString(1, name);
        	preparedStatement.setInt(2, age);
        	preparedStatement.setString(3,gender);
        	preparedStatement.setDate(4, updatedDate);
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " rows inserted");
            PreparedStatement lastInsertedIdStmt = connection.prepareStatement("SELECT LAST_INSERT_ID();");
           ResultSet rs = lastInsertedIdStmt.executeQuery();
	          if(rs.next()) {
	        	  Integer id = rs.getInt(1);
	              return id;
	          }
           

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
