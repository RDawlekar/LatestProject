package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectivity {
	
	 private static final String URL= "jdbc:mysql://localhost:5432/mydb";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "password";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    }
	    public static void main(String[] args) {
	        try (Connection conn = getConnection()) {
	            System.out.println("Connected: " + !conn.isClosed());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


