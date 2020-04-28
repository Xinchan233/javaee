package class12.jdbc;

import class12.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentJdbc {

    private static ApplicationContext ct;
    static{
        ct=new AnnotationConfigApplicationContext(Student.class);
    }
    public static void main(String[] args) {

        List<Student> list = selectAll();

    }

    public Student addstudent(Student h){

        String sqlString = "insert into s_student (id,name,create_time) values(?,?,?)";
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
        DataSource ds = (DataSource)ac.getBean("datasource");

        Connection connection =null;
        try{
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(sqlString)) {
                ps.setString(1,h.getId());
                ps.setString(2,h.getName());
                ps.setTimestamp(3,new Timestamp(h.getCreateTime().getTime()));
                ps.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                if(connection!=null) {
                    connection.rollback();
                }
            } catch (Exception e2) {
                // handle exception
            }
        }finally{
            try {
                if(connection!=null) {
                    connection.rollback();
                }
            } catch (Exception e2) {
                //handle exception
            }
        }

        return h;

    }

    public static List<Student> selectAll(){

        String sqlString = "SELECT * FROM s_student";
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
        DataSource ds = (DataSource)ac.getBean("datasource");

        List<Student> list = new ArrayList<>();
        try(Connection connection =  ds.getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Student sh=(Student) ct.getBean("Student");
                        sh.setId(resultSet.getString("id"));
                        sh.setName(resultSet.getString("name"));
                        sh.setCreateTime(resultSet.getTimestamp("create_time"));
                        list.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
