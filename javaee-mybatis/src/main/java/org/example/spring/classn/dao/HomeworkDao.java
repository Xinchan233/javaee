package org.example.spring.mvc.dao;

import org.apache.ibatis.annotations.Select;
import org.example.spring.mvc.model.Homework;

import java.util.List;

public interface HomeworkDao {

    boolean add(Homework homework);

    boolean delete(Homework homework);

    boolean change(Homework homework);

    List<Homework> selectAll();

    Homework selectById(String id);


}
