package org.example;

import java.util.List;

public abstract class Student {
    public String name;
    public int age;
    public String department;
    public int id;
    public String major;
    public String type;

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
    public String getMajor(String major) {
        return major;

    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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

    public String getMajor() {
        return major;
    }

}
