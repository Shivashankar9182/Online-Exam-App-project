package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    // Method to register a new user
    public static boolean registerUser(String username, String password) {
        // Check if the username is already using the same password
        if (isPasswordUsed(username, password)) {
            System.out.println("Error: This username cannot use the same password again.");
            return false; 
        }

        // Proceed with registration if validation is passed
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query); 
            stmt.setString(1, username); // Set the username parameter
            stmt.setString(2, password); // Set the password parameter
            stmt.executeUpdate(); // Execute the update

            System.out.println("Registration successful!");
            return true; 
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    // Method to check if the password is already used by the username
    public static boolean isPasswordUsed(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) { // Establish database connection
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query); // Prepare the statement
            stmt.setString(1, username); // Set the username parameter
            stmt.setString(2, password); // Set the password parameter
            ResultSet rs = stmt.executeQuery(); // Execute the query and get the result set
            return rs.next(); // True if the username is found with the same password, otherwise false
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    // Method to log in a user
    public static boolean loginUser(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) { 
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query); 
            stmt.setString(1, username); // Set the username parameter
            stmt.setString(2, password); // Set the password parameter
            ResultSet rs = stmt.executeQuery(); // Execute the query and get the result set
            return rs.next(); // True if user found, otherwise false
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false; 
        }
    }

    	public static int getUserId(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

		public static void displayUserInfo(int userId) {
			// TODO Auto-generated method stub
			
		}
  }

	
	
