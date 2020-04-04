package class12.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Databasepool {
    static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikariDateSource(){

        if(null!=hikariDataSource){
        return hikariDataSource;
    }

        synchronized (Databasepool.class){
            if(null == hikariDataSource){
                String jdbcurl="jdbc:mysql://127.0.0.1:3306/school";
                String drivername="com.mysql.cj.jdbc.Driver";

                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setUsername("root");
                hikariConfig.setPassword("123456");

                hikariConfig.setDriverClassName(drivername);
                hikariConfig.setJdbcUrl(jdbcurl);

                hikariDataSource = new HikariDataSource(hikariConfig);
                return hikariDataSource;
            }
        }

        return null;


    }
    public static void main(String[] args){
        getHikariDateSource();
    }

}
