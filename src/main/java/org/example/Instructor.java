package org.example;

import java.util.*;

public class Instructor {
    private String name;
    private String department;
    private List<Course> assignedCourses;
    private Map<Course, Set<Student>> courseRoster;


    public Instructor(String name, String department) {
        this.name = name;
        this.department = department;
        this.assignedCourses = new ArrayList<>();
        this.courseRoster = new HashMap<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public List<Course> getAssignedCourses() { return assignedCourses; }
    public Map<Course, Set<Student>> getCourseRoster() { return courseRoster; }

    public void assignCourse(Course c) {
        assignedCourses.add(c);
        courseRoster.put(c, c.getEnrolledStudents());

    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", assignedCourses=" + assignedCourses.size() +
                '}';
    }
}
