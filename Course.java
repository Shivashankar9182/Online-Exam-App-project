package project;

import java.sql.*;

public class Course {
    // Method to list all courses
    public static void listCourses() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Establish database connection
            String query = "SELECT * FROM course"; // SQL query to select all courses
            Statement stmt = conn.createStatement(); // Create a statement
            ResultSet rs = stmt.executeQuery(query); // Execute the query and get the result set
            while (rs.next()) { // Iterate through the result set
                // Print course ID and course name for each course
                System.out.println("Course ID: " + rs.getInt("course_id") + ", Name: " + rs.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
    }

    // Method to join a course for a given user ID and course ID
    public static boolean joinCourse(int userId, int courseId) {
        try (Connection conn = DatabaseConnection.getConnection()) { // Establish database connection
            String query = "INSERT INTO joinCourse (user_id, course_id) VALUES (?, ?)"; // SQL query to insert user-course relation
            PreparedStatement stmt = conn.prepareStatement(query); // Prepare the statement
            stmt.setInt(1, userId); // Set user ID parameter
            stmt.setInt(2, courseId); // Set course ID parameter
            stmt.executeUpdate(); // Execute the update
            return true; // Return true if the update is successful
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
            return false; // Return false if there is an exception
        }
    }

    // Placeholder method for listing available exams
    public static void listAvailableExams() {
        // This method is currently not implemented
    }
}
