package cn.drelang.j2ee.jdbc;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.util.Properties;

/**
 * 对 JDBC 的一个简单封装
 *
 * Created by Drelang on 2019/9/23 10:34
 */

public class JDBCUtil {
    static final String PROPERTY_FILE = "db.properties";
    static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取 sql 连接
    public static Connection getMysqlCoon() {
        try {
            // 注册驱动，只需要做一次
            Class.forName(prop.getProperty("mysqlDriver"));
            // 建立连接
            return DriverManager.getConnection(prop.getProperty("mysqlURL"),
                                prop.getProperty("mysqlUser"), prop.getProperty("mysqlPwd"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 关闭连接
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        // 关闭 ResultSet
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 关闭 Statement
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 关闭 Connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
