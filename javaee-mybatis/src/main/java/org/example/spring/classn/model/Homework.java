package org.example.spring.mvc.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Homework {
    private long id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;

}
