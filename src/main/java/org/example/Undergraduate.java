package org.example;

import java.util.List;

public class Undergraduate extends Student {
    public Undergraduate(String name, int age, String department, int id) {
        super(name, age, department, id);
    }

    @Override
    public double calculateGPA(List<Course> courses) {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Course c : courses) {
            totalPoints += c.getCredits() * 1.0;
            totalCredits += c.getCredits();
        }
        return totalCredits == 0 ? 0 : Math.round((totalPoints / totalCredits) * 100.0) / 100.0;
    }
}

