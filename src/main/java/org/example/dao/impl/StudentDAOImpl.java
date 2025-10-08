package org.example.dao.impl;

import org.example.Student;
import org.example.Graduate;
import org.example.dao.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    // Add new student
    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students (id, name, age, department, type) VALUES (?, ?, ?,?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getDepartment());
            ps.setString(5, student.getType());
            ps.executeUpdate();
            System.out.println(" Student added in database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all students
    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student s = new Graduate(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getInt("id")
                );
                s.setMajor(rs.getString("major"));
                s.setType(rs.getString("type"));
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update student by id
    @Override
    public void update(int id, Student student) {
        String sql = "UPDATE students SET name=?, age=?, department=?, major=?, type=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getMajor());
            ps.setString(5, student.getType());
            ps.setInt(6, id);
            ps.executeUpdate();
            System.out.println(" Student updated in database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Student deleted from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Graduate(
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("department"),
                            rs.getInt("id")
                    );
                    s.setMajor(rs.getString("major"));
                    s.setType(rs.getString("type"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
