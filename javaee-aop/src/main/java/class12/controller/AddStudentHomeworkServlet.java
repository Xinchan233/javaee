package class12.controller;

import class12.jdbc.StudentHomeworkJdbc;
import class12.model.StudentHomework;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Aspect
@Controller
public class AddStudentHomeworkServlet extends HttpServlet {
    private static ApplicationContext ct;
    static{
        ct=new AnnotationConfigApplicationContext(StudentHomework.class);
    }

    @RequestMapping(value = "/AddStudentHomeworkServlet",method = RequestMethod.POST)
    protected String post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码

        StudentHomework st = (StudentHomework)ct.getBean("StudentHomework");

        st.setStudentId(req.getParameter("id"));
        st.setHomeworkId(req.getParameter("hwid"));
        st.setHomeworkTitle(req.getParameter("hwname"));
        st.setHomeworkContent(req.getParameter("content"));
        Date date = new Date();
        st.setCreateTime(date);
        System.out.println(req.getParameter("content"));
        ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("app-servlet.xml");
        StudentHomeworkJdbc sj=ac.getBean(StudentHomeworkJdbc.class);
        sj.addStudentHomework(st);
        return "index.jsp";
    }
}
