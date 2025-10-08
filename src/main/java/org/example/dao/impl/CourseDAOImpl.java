package org.example.dao.impl;

import org.example.Course;
import org.example.dao.CourseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private Connection conn;

    public CourseDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Course course) {
        String sql = "INSERT INTO courses (title, description, credits) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setInt(3, course.getCredits());
            ps.executeUpdate();
            System.out.println("✅ Course added in database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all courses
    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("credits")
                );
                courses.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Update course by title
    @Override
    public void update(String title, Course course) {
        String sql = "UPDATE courses SET title=?, description=?, credits=? WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setInt(3, course.getCredits());
            ps.setString(4, title);
            ps.executeUpdate();
            System.out.println(" Course updated in database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete course by title
    @Override
    public void delete(String title) {
        String sql = "DELETE FROM courses WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println(" Course deleted from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find course by title
    @Override
    public Course findByTitle(String title) {
        String sql = "SELECT * FROM courses WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("credits")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Check if course exists
    @Override
    public boolean existsByTitle(String title) {
        String sql = "SELECT 1 FROM courses WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
