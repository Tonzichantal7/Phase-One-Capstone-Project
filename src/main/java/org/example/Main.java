package org.example;

import org.example.dao.CourseDAO;
import org.example.dao.StudentDAO;
import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.StudentDAOImpl;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection(); Scanner sc = new Scanner(System.in)) {

            StudentDAO studentDAO = new StudentDAOImpl(conn);
            CourseDAO courseDAO = new CourseDAOImpl(conn);

            String choice;
            do {
                System.out.println("\nUniversity Management");
                System.out.println("1. Add Student");
                System.out.println("2. Add Course");
                System.out.println("3. Remove Student");
                System.out.println("4. Remove Course");
                System.out.println("5. Enroll Student in Course");
                System.out.println("6. View Students with Courses");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextLine();

                switch (choice) {

                    case "1":
                        System.out.print("Name: ");
                        String sName = sc.nextLine();
                        System.out.print("Age: ");
                        int sAge = Integer.parseInt(sc.nextLine());
                        System.out.print("Department: ");
                        String sDept = sc.nextLine();
                        System.out.print("Major: ");
                        String sMajor = sc.nextLine();
                        System.out.print("Type (Graduate/Undergraduate): ");
                        String sType = sc.nextLine();

                        String addStudentSQL = "INSERT INTO students (name, age, department, major, type) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement ps = conn.prepareStatement(addStudentSQL, Statement.RETURN_GENERATED_KEYS)) {
                            ps.setString(1, sName);
                            ps.setInt(2, sAge);
                            ps.setString(3, sDept);
                            ps.setString(4, sMajor);
                            ps.setString(5, sType);
                            ps.executeUpdate();

                            ResultSet rs = ps.getGeneratedKeys();
                            if (rs.next()) {
                                System.out.println("Student added with ID: " + rs.getInt(1));
                            }
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;

                    case "2":
                        System.out.print("Course Title: ");
                        String cTitle = sc.nextLine();
                        System.out.print("Description: ");
                        String cDesc = sc.nextLine();
                        System.out.print("Credits: ");
                        int cCredits = Integer.parseInt(sc.nextLine());

                        String addCourseSQL = "INSERT INTO courses (title, description, credits) VALUES (?, ?, ?)";
                        try (PreparedStatement ps = conn.prepareStatement(addCourseSQL)) {
                            ps.setString(1, cTitle);
                            ps.setString(2, cDesc);
                            ps.setInt(3, cCredits);
                            ps.executeUpdate();
                            System.out.println("Course added");
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;

                    case "3":
                        System.out.print("Enter Student ID to delete: ");
                        int delStudentId = Integer.parseInt(sc.nextLine());
                        String delStudentSQL = "DELETE FROM students WHERE id = ?";
                        try (PreparedStatement ps = conn.prepareStatement(delStudentSQL)) {
                            ps.setInt(1, delStudentId);
                            ps.executeUpdate();
                            System.out.println("Student deleted");
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;

                    case "4":
                        System.out.print("Enter Course ID to delete: ");
                        int delCourseId = Integer.parseInt(sc.nextLine());
                        String delCourseSQL = "DELETE FROM courses WHERE id = ?";
                        try (PreparedStatement ps = conn.prepareStatement(delCourseSQL)) {
                            ps.setInt(1, delCourseId);
                            ps.executeUpdate();
                            System.out.println("Course deleted");
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;

                    case "5":
                        System.out.print("Enter Student ID: ");
                        int studentId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Course Title: ");
                        String courseTitle = sc.nextLine();

                        int courseId = -1;
                        String findCourseSQL = "SELECT id FROM courses WHERE title = ?";
                        try (PreparedStatement ps = conn.prepareStatement(findCourseSQL)) {
                            ps.setString(1, courseTitle);
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                courseId = rs.getInt("id");
                            } else {
                                System.out.println("Course not found");
                            }
                        }

                        if (courseId != -1) {
                            String enrollSQL = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
                            try (PreparedStatement ps = conn.prepareStatement(enrollSQL)) {
                                ps.setInt(1, studentId);
                                ps.setInt(2, courseId);
                                ps.executeUpdate();
                                System.out.println("Student enrolled");
                            } catch (SQLException e) {
                                System.out.println("Database error: " + e.getMessage());
                            }
                        }
                        break;

                    case "6":
                        String viewSQL = """
                                SELECT s.name AS student_name, c.title AS course_name
                                FROM students s
                                JOIN enrollments e ON s.id = e.student_id
                                JOIN courses c ON c.id = e.course_id
                                ORDER BY s.name;
                                """;
                        try (PreparedStatement ps = conn.prepareStatement(viewSQL);
                             ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                System.out.println(rs.getString("student_name") + " -> " + rs.getString("course_name"));
                            }
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;

                    case "0":
                        System.out.println("Exiting");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } while (!choice.equals("0"));

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}

