package class12.controller;

import class12.jdbc.HomeworkJdbc;
import class12.model.Homework;
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
public class PostHomework extends HttpServlet {
    private static ApplicationContext ct;
    static{
        ct=new AnnotationConfigApplicationContext(Homework.class);
    }
    @RequestMapping(value = "/postHomework",method = RequestMethod.POST)
    protected String post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码

        Homework hk = (Homework)ct.getBean("Homework");

        hk.setTitle(req.getParameter("title"));
        hk.setContent(req.getParameter("content"));
        Date date = new Date();
        hk.setCreateTime(date);

        ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("app-servlet.xml");
        HomeworkJdbc sj=ac.getBean(HomeworkJdbc.class);
        sj.addHomework(hk);

        return "allhomework.jsp";
    }
}