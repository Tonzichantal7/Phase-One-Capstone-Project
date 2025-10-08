package org.example.dao;

import org.example.Student;
import java.util.List;

public interface StudentDAO extends BaseDAO<Student> {
    void add(Student student);
    List<Student> getAll();
    void update(int id, Student student);
    void delete(int id);
    Student findById(int id);

    default boolean existsById(int id) {
        return false;
    }
}
