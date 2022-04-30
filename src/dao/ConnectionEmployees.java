package dao;

import java.sql.*;

public class ConnectionEmployees {

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees",
                    "root",
                    "05uMz&ugJ@5#BWzcq8pw");
            System.out.println("Connection established");
            PreparedStatement stmt = con.prepareStatement("select * from employees where gender = ? limit ?");
            stmt.setString(1, "F");
            stmt.setInt(2, 4);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("--------------------------------------");
                System.out.println("Employee ID: [" + rs.getInt(1) + "]");
                System.out.println("Birthdate: [" + rs.getString(2)+"]");
                System.out.println("First Name: [" + rs.getString(3) + "]");
                System.out.println("Last Name: [" + rs.getString(4) + "]");
                System.out.println("--------------------------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}