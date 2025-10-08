package org.example;

import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.StudentDAOImpl;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        try (Connection conn = DBConnection.getConnection()) {

            if (conn == null) {
                System.out.println("Cannot connect to database. Exiting...");
                return;
            }


            CourseDAOImpl courseDAO = new CourseDAOImpl(conn);
            StudentDAOImpl studentDAO = new StudentDAOImpl(conn);


            System.out.println("=== COURSE OPERATIONS ===");


            Course javaCourse = new Course("Java Programming", "Learn OOP & JDBC", 4);
            Course pythonCourse = new Course("Python Programming", "Learn Python basics", 3);
            courseDAO.add(javaCourse);
            courseDAO.add(pythonCourse);


            System.out.println("All courses in database:");
            List<Course> courses = courseDAO.getAll();
            for (Course c : courses) {
                System.out.println(c.getCourseName() + " | " + c.getCourseCode() + " | " + c.getCredits());
            }


            Course updatedJava = new Course("Java Programming", "OOP & JDBC Advanced", 5);
            courseDAO.update("Java Programming", updatedJava);
            System.out.println("Updated Java course!");


            Course foundCourse = courseDAO.findByTitle("Java Programming");
            if (foundCourse != null) {
                System.out.println("Found course: " + foundCourse.getCourseName());
            }


            courseDAO.delete("Python Programming");
            System.out.println("Deleted Python course!");

            System.out.println("\n=== STUDENT OPERATIONS ===");


            Student grad1 = new Graduate("Alice", 25, "Computer Science", 1);
            grad1.setMajor("Software Engineering");
            grad1.setType("Graduate");

            Student grad2 = new Graduate("Bob", 27, "Information Technology", 2);
            grad2.setMajor("Cybersecurity");
            grad2.setType("Graduate");

            studentDAO.add(grad1);
            studentDAO.add(grad2);


            System.out.println("All students in database:");
            List<Student> students = studentDAO.getAll();
            for (Student s : students) {
                System.out.println(s.getName() + " | " + s.getAge() + " | " + s.getDepartment() + " | " + s.getMajor() + " | " + s.getType());
            }


            grad1.setDepartment("Software Engineering");
            grad1.setMajor("Full Stack Development");
            studentDAO.update(1, grad1);
            System.out.println("Updated student Alice!");


            Student foundStudent = studentDAO.findById(1);
            if (foundStudent != null) {
                System.out.println("Found student: " + foundStudent.getName() + " | " + foundStudent.getDepartment());
            }


            studentDAO.delete(2);
            System.out.println("Deleted student Bob!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
