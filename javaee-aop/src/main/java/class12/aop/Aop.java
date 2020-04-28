package class12.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy
@Component
public class Aop {

    @Pointcut("execution(* class12.jdbc.StudentJdbc.*(..))")
    private void studentPointcut() {
    }

    @Before(value = "studentPointcut()")
    private void beforelogst(){
        System.out.println("before添加学生");
    }

    @AfterReturning("studentPointcut()")
    public void afterlogst(){
        System.out.println("after添加学生");

    }

    @Pointcut("execution(* class12.jdbc.StudentHomeworkJdbc.*(..))")
    private void studenthwPointcut() {
    }

    @Before(value = "studenthwPointcut()")
    private void beforelogsthw(){
        System.out.println("before添加学生作业");
    }

    @AfterReturning("studenthwPointcut()")
    public void afterlogsthw(){
        System.out.println("after添加学生作业");

    }

    @Pointcut("execution(* class12.jdbc.HomeworkJdbc.*(..))")
    private void hwPointcut() {
    }

    @Before(value = "studenthwPointcut()")
    private void beforeloghw(){
        System.out.println("before添加作业");
    }

    @AfterReturning("studenthwPointcut()")
    public void afterloghw(){
        System.out.println("after添加作业");

    }


}
