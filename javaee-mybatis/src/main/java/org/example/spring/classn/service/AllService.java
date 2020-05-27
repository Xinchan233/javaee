package org.example.spring.mvc.service;

import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.StudentHomework;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AllService {

    boolean addHomework(HttpServletRequest request);

    boolean addStudent(HttpServletRequest request);

    boolean addStudentHomework(HttpServletRequest req);

    List<StudentHomework> selectAll(String id);

    List<Homework> showHomework();

    Homework showHomeworkDetails(String id);
}
