package org.example.spring.mvc.dao;

import org.example.spring.mvc.model.StudentHomework;

import java.util.List;

public interface StudentHomeworkDao {

    boolean add(StudentHomework studentHomework);

    boolean delete(StudentHomework studentHomework);

    boolean edit(StudentHomework studentHomework);

    List<StudentHomework> getAll(String id);
}
