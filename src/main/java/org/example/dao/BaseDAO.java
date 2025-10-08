package org.example.dao;

import java.util.List;

public interface BaseDAO<T> {
    void add(T t);           // Add a new record
    List<T> getAll();        // Get all records
    void update(T t);        // Update a record
    void delete(int id);     // Delete by ID
}
