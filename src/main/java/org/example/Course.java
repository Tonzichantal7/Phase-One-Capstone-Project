package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course {
    private String courseName;
    private String courseCode;
    private int credits;
    private  Set<Student> enrolledStudents;

    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.enrolledStudents = new HashSet<>();
    }

    // Getters and Setters
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Set<Student> getEnrolledStudents() { return (Set<Student>) enrolledStudents; }

    public void enrollStudent(Student s) {
        enrolledStudents.add(s);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", credits=" + credits +
                ", enrolledStudents=" + enrolledStudents.size() +
                '}';
    }


}
