package class12.controller;

import class12.jdbc.StudentJdbc;
import class12.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Controller
public class Addstudent {
    @Autowired
    private StudentJdbc studentJdbc;
    private static ApplicationContext ct;
    static{
        ct=new AnnotationConfigApplicationContext(Student.class);
    }

    @RequestMapping(value = "/addstudent",method = RequestMethod.POST)
    protected String addstudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码

        Student st = (Student)ct.getBean("Student");
        st.setId(req.getParameter("id"));
        st.setName(req.getParameter("name"));
        Date date = new Date();
        st.setCreateTime(date);

        studentJdbc.addstudent(st);
        System.out.println("添加学生："+st.getName());

        return "allstudent.jsp";
    }
}