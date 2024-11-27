package project;

import java.sql.*;
import java.util.Scanner;

public class Exam {
    // Method to list available exams for a given course ID
    public static void listAvailableExams(int courseId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT exam_id, exam_name FROM exams WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Available Exams for Course ID " + courseId + ":");
            boolean hasExams = false; // To check if exams are available
            while (rs.next()) {
                hasExams = true; // There are available exams
                System.out.println("Exam ID: " + rs.getInt("exam_id") + ", Exam Name: " + rs.getString("exam_name"));
            }
            if (!hasExams) {
                System.out.println("No exams available for this course.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to attempt an exam for a given user and exam ID
    public static void attemptExam(int userId, int examId) {
        Scanner scanner = new Scanner(System.in);
        int score = 0; // Initialize score

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Fetch questions for the exam
            String questionQuery = "SELECT question_id, question_text, correct_option FROM questions WHERE exam_id = ?";
            PreparedStatement questionStmt = conn.prepareStatement(questionQuery);
            questionStmt.setInt(1, examId);
            ResultSet questionRs = questionStmt.executeQuery();

            // Iterate through questions
            while (questionRs.next()) {
                int questionId = questionRs.getInt("question_id");
                String questionText = questionRs.getString("question_text");
                String correctOption = questionRs.getString("correct_option");

                System.out.println("\n" + questionText);

                // Fetch options for each question
                String optionsQuery = "SELECT option_text, is_correct FROM options WHERE question_id = ?";
                PreparedStatement optionsStmt = conn.prepareStatement(optionsQuery);
                optionsStmt.setInt(1, questionId);
                ResultSet optionsRs = optionsStmt.executeQuery();

                char optionLetter = 'A';
                // Iterate through options
                while (optionsRs.next()) {
                    String optionText = optionsRs.getString("option_text");
                    System.out.println(optionLetter + ". " + optionText);

                    optionLetter++;
                }

                System.out.print("Your answer (A/B/C/D): ");
                String userInput = scanner.next();

                // Check if user's answer is correct
                if (userInput.equalsIgnoreCase(correctOption)) {
                    score++; // Increment score for correct answer
                }
            }

            // Insert the result into the `results` table
            String resultQuery = "INSERT INTO results (user_id, exam_id, score) VALUES (?, ?, ?)";
            PreparedStatement resultStmt = conn.prepareStatement(resultQuery);
            resultStmt.setInt(1, userId);
            resultStmt.setInt(2, examId);
            resultStmt.setInt(3, score);
            resultStmt.executeUpdate();

            System.out.println("Test completed! Your score: " + score);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check results for a given user ID
    public static void checkResults(int userId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT e.exam_name, r.score FROM results r JOIN exams e ON r.exam_id = e.exam_id WHERE r.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nResults:");
            // Iterate through results
            while (rs.next()) {
                String examName = rs.getString("exam_name");
                int score = rs.getInt("score");
                String resultStatus;
                if (score < 3) {
                    resultStatus = "Fail";
                } else {
                    resultStatus = "Pass";
                }
                System.out.println("Exam: " + examName + " | Score: " + score + "/5");
                System.out.println("Result: " + resultStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
