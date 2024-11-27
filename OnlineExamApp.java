package project;

import java.util.Scanner;

public class OnlineExamApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object to read user input
        boolean exit = false; // Boolean flag to control the loop
        boolean loggedIn = false; // Track if the user is logged in
        int userId = -1; // Store the logged-in user's ID

        System.out.println("Welcome to the Online Exam System!");

        while (!exit) { // Loop until the user chooses to exit
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Join a Course");
            System.out.println("4. Attempt an Exam");
            System.out.println("5. Check Results");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); 

            switch (choice) {
                case 1: // Register
                    String username, password;
                    while (true) {
                        System.out.print("Enter username: ");
                        username = scanner.next(); // Read username
                        System.out.print("Enter password: ");
                        password = scanner.next(); // Read password

                        // Check if username and password are different
                        if (username.equals(password)) {
                            System.out.println("Error: Username and password must not be the same. Please try again.");
                        } else {
                            break; // Exit the loop if the username and password are valid
                        }
                    }

                    // Attempt to register the user
                    if (User.registerUser(username, password)) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 2: // Login
                    if (!loggedIn) {
                        System.out.print("Enter username: ");
                        username = scanner.next(); // Read username
                        System.out.print("Enter password: ");
                        password = scanner.next(); // Read password

                        // Check login credentials
                        if (User.loginUser(username, password)) {
                            System.out.println("Login successful!");
                            loggedIn = true;
                            userId = User.getUserId(username); // Assume this method retrieves the user's ID based on their username
                        } else {
                            System.out.println("Invalid credentials.");
                        }
                    } else {
                        System.out.println("Already logged in.");
                    }
                    break;

                case 3: // Join a Course
                    if (loggedIn) {
                        Course.listCourses(); // Display list of available courses
                        System.out.print("Enter Course ID to join: ");
                        int courseId = scanner.nextInt(); // Read course ID to join

                        if (Course.joinCourse(userId, courseId)) {
                            System.out.println("Course joined successfully!");
                        } else {
                            System.out.println("Failed to join course.");
                        }
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 4: // Attempt an Exam
                    if (loggedIn) {
                        System.out.print("Enter Course ID to view available exams: ");
                        int courseIdForExams = scanner.nextInt(); // Read course ID to view exams
                        Exam.listAvailableExams(courseIdForExams); // Display list of available exams

                        System.out.print("Enter Exam ID to attempt: ");
                        int examId = scanner.nextInt(); // Read exam ID to attempt
                        Exam.attemptExam(userId, examId); // Attempt the exam
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 5: // Check Results
                    if (loggedIn) {
                    	System.out.print("Enter your User ID: ");
                        userId = scanner.nextInt(); // Read user ID
                        Exam.checkResults(userId); // Check results for the user
                        } else {
                        System.out.println("Please log in first.");
                    }
                    break;

                case 6: // Logout
                    if (loggedIn) {
                        loggedIn = false;
                        userId = -1;
                        System.out.println("Logged out successfully.");
                    } else {
                        System.out.println("You are not logged in.");
                    }
                    break;

                case 7: // Exit
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        scanner.close(); 
    }
}
