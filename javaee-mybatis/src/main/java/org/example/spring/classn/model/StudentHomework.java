package org.example.spring.mvc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StudentHomework {
    private long id;
    private long studentId;
    private long homeworkId;
    private String homeworkTitle;
    private String homeworkContent;
    private Date createTime;
    private Date updateTime;
}
