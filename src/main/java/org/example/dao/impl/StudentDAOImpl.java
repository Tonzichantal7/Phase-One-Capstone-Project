package org.example.dao.impl;

import org.example.Student;
import org.example.dao.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students (name, age, department, major, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getMajor());
            ps.setString(5, student.getType());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getInt("id")) {
                    @Override
                    public double calculateGPA(List<org.example.Course> courses) {
                        return 0;
                    }
                };
                s.setMajor(rs.getString("major"));
                s.setType(rs.getString("type"));
                students.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getInt("id")) {
                    @Override
                    public double calculateGPA(List<org.example.Course> courses) {
                        return 0;
                    }
                };
                s.setMajor(rs.getString("major"));
                s.setType(rs.getString("type"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM students WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
