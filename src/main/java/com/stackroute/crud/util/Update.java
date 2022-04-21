package com.stackroute.crud.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Update {

    public void updateOperation(int id, String name, int age, String gender){

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
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET name=?, age=?, gender=?, updated_time=? WHERE id=?");) {

        	preparedStatement.setString(1, name);
        	preparedStatement.setInt(2, age);
        	preparedStatement.setString(3,gender);
        	preparedStatement.setDate(4, updatedDate);
        	preparedStatement.setInt(5, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows updated");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
