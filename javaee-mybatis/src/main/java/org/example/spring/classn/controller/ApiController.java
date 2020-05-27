package org.example.spring.mvc.controller;

import org.example.spring.mvc.service.AllServiceImpl;
import org.example.spring.mvc.model.Homework;
import org.example.spring.mvc.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ApiController {

    private final AllServiceImpl allService;
    @Autowired
    public ApiController(AllServiceImpl allService) {
        this.allService = allService;
    }

    @RequestMapping(path = "/showHomework", method = RequestMethod.GET)
    public ModelAndView showHomework(){
        List<Homework> list = allService.showHomework();
        return new ModelAndView("index.jsp", "list", list);
    }


    @RequestMapping(path = "/allHomework", method = RequestMethod.GET)
    public ModelAndView allHomework(){
        //读取所有作业内容
        List<Homework> list = allService.showHomework();
        return new ModelAndView("allhomework.jsp", "list", list);
    }

    @RequestMapping(path = "/query", method = RequestMethod.GET)
    public ModelAndView query(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = request.getParameter("id");
        //从数据库读取指定作业id的所有记录
        List<StudentHomework> list = allService.selectAll(id);
        return new ModelAndView("jsp/homeworkSubmission.jsp", "list", list);
    }

    @RequestMapping(path = "/addHomework", method = RequestMethod.POST)
    public void addHomework(){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();
        boolean result = allService.addHomework(request);

        //用来判断是否添加作业成功
        request.setAttribute("isOK", result);
        request.setAttribute("type","addHomework");
        try {
            request.getRequestDispatcher("allhomework.jsp").forward(request,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/addStudent", method = RequestMethod.POST)
    public void addStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        boolean result = allService.addStudent(request);

        //用来判断是否添加作业成功
        request.setAttribute("isOK", result);
        request.setAttribute("type","addStudent");
        try {
            request.getRequestDispatcher("allstudent.jsp").forward(request,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/willSubmit", method = RequestMethod.GET)
    public ModelAndView willSubmit(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = request.getParameter("id");

        Homework homework = allService.showHomeworkDetails(id);
        return new ModelAndView("addhomework.jsp", "homework", homework);
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public void submit(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        boolean result = allService.addStudentHomework(req);
        //用于判断是否提交成功
        req.setAttribute("isOK", result);
        req.setAttribute("type","addStudentHomework");
        try {
            req.getRequestDispatcher("result.jsp").forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }


}
