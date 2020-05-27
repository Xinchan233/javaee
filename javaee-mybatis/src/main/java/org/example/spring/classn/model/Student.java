package org.example.spring.mvc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Student {
    private long id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
