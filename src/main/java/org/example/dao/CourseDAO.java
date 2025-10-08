package org.example.dao;

import org.example.Course;
import java.util.List;

public interface CourseDAO {
    void add(Course course);
    List<Course> getAll();

    void update(String title, Course course);

    // Delete course by title
    void delete(String title);

    Course findByTitle(String title);
    boolean existsByTitle(String title);
}
