package class12.jdbc;

import class12.model.Homework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class HomeworkJdbc {
    private static ApplicationContext ct;
    static{
        ct=new AnnotationConfigApplicationContext(Homework.class);
    }

    public static void main(String[] args) {


        List<Homework> list = selectAll();

        for (Homework h : list){
            System.out.println(h.getContent());
        }
    }

    public Homework addHomework(Homework h){

        String sqlString = "insert into s_homework (title,content,create_time) values(?,?,?)";
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
        DataSource ds = (DataSource)ac.getBean("datasource");

        Connection connection =null;
        try{
            connection = ds.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sqlString)) {
                ps.setString(1,h.getTitle());
                ps.setString(2,h.getContent());
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

    public static List<Homework> selectAll(){

        String sqlString = "SELECT * FROM s_homework";

        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
        DataSource ds = (DataSource)ac.getBean("datasource");

        List<Homework> list = new ArrayList<>();
        try(Connection connection =  ds.getConnection()) {
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sqlString)){
                    // 获取执行结果
                    while (resultSet.next()){
                        Homework sh =(Homework)ct.getBean("Homework");
                        sh.setId(resultSet.getLong("id"));
                        sh.setTitle(resultSet.getString("title"));
                        sh.setContent(resultSet.getString("content"));
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
