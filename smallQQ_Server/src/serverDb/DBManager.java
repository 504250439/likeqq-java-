package serverDb;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 *  连接数据库
 *  创建线程池
 *
 *
 */



public class DBManager {
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "father";
    public static final String URL = "jdbc:mysql://localhost:3306/smallqq?useSSL=false&serverTimezone=UTC";
    public static DataSource dataSource = null;

    static {
        try {
            //数据连接池
            ComboPooledDataSource pool = new ComboPooledDataSource();
            pool.setDriverClass(DRIVER_NAME);
            pool.setUser(USERNAME);
            pool.setPassword(PASSWORD);
            pool.setJdbcUrl(URL);
            pool.setMaxPoolSize(30);
            pool.setMinPoolSize(5);
            dataSource = pool;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据连接池加载失败!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
