package com.example.demo.jdbcpractice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the SQL Server JDBC driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // Connect to the MSDB database
        Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=movies;encrypt=true;trustServerCertificate=true;",
                "SA", "<YourStrong@Passw0rd>");
        // SQL statement to create a new table
        String createTableSQL = "CREATE TABLE Employees ("
                + "EmployeeID INT PRIMARY KEY IDENTITY(1,1), "
                + "FirstName NVARCHAR(50) NOT NULL, "
                + "LastName NVARCHAR(50), "
                + "Position NVARCHAR(50), "
                + "Salary DECIMAL(18, 2), "
                + "HireDate DATE"
                + ")";
        try (Statement stmt = con.createStatement()) {
            // Execute the SQL statement to create the table
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'Employees' created successfully in the MSDB database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }
}