package org.example;

import java.util.List;

public abstract class Student {
    private String name;
    private int age;
    private String department;
    private int id;

    public Student(String name, int age, String department, int id) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }


    public abstract double calculateGPA(List<Course> courses);

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", id=" + id +
               '}';
    }

}
