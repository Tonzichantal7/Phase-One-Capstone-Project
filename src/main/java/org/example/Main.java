package org.example;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Course course1 = new Course("Java", "CS101", 3);
        Course course2 = new Course("Python", "CS102", 3);
        Course course3 = new Course("C++", "CS103", 3);
        Undergraduate student1 = new Undergraduate("John", 20, "CS", 101);
        Undergraduate student2 = new Undergraduate("Jane", 21, "CS", 102);
        Undergraduate student3 = new Undergraduate("Joe", 22, "CS", 103);
        Graduate student4 = new Graduate("Mike", 23, "CS", 104);
        Graduate student5 = new Graduate("Mary", 24, "CS", 105);

        course2.enrollStudent(student1);
        course2.enrollStudent(student2);
        course2.enrollStudent(student3);
        course3.enrollStudent(student4);
        course3.enrollStudent(student5);
        course1.enrollStudent(student1);
        course1.enrollStudent(student2);
        course1.enrollStudent(student3);
        Instructor instructor1 = new Instructor("John", "CS");
        instructor1.assignCourse(course1);
        instructor1.assignCourse(course2);
        instructor1.assignCourse(course3);
        System.out.println(instructor1);
        System.out.println(" assigned courses: " + instructor1.getAssignedCourses().size());
        System.out.println(" roster size: " + instructor1.getCourseRoster().size());
        System.out.println(student1.getName() + " GPA " + student1.calculateGPA(instructor1.getAssignedCourses()));
        System.out.println(student2.getName() + " GPA " + student2.calculateGPA(instructor1.getAssignedCourses()));

        List<Course> johnCourses = Arrays.asList(course1, course2, course3);
        System.out.println(student1.getName() + " GPA " + student1.calculateGPA(johnCourses));
        System.out.println(student2.getName() + " GPA " + student2.calculateGPA(johnCourses));

    }
}