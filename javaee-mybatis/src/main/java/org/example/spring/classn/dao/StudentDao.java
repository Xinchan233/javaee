package org.example.spring.mvc.dao;

import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.Student;

import java.util.List;

public interface StudentDao {

    boolean add(Student student);

    boolean delete(Student student);

    List<Student> select();
}
