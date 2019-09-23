package cn.drelang.j2ee.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Drelang on 2019/9/23 10:54
 */

public class JDBCTest {
    static final Logger log = LoggerFactory.getLogger(JDBCTest.class);
    
    static Connection conn = JDBCUtil.getMysqlCoon();

    public static void main(String[] args) {
        log.info("Test delete all record: ");
        int res = deleteAll();
        log.info("result="+res);
        
        log.info("Test insert one record: ");
        int result = create();
        log.info("result="+result);

        log.info("Test get one record: ");
        ResultSet set = read();
        assert set != null;
        try {
            log.info("iterate result set");
            int columnCount = set.getMetaData().getColumnCount();
            while (set.next()) {
                String columnName;
                for (int i=1; i<=columnCount; i++) {
                    columnName = set.getMetaData().getColumnName(i);
                    System.out.println(columnName + ": " + set.getString(columnName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 测试删除表格内的内容
    private static int deleteAll() {
        String sql = "delete from user";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 测试插入一条数据，若成功，返回 1
    private static int create() {
        String sql = "insert into user (stuid, username, password_jwt, mobile, card_id, rank, created_at) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "174519");  // 占位符顺序从 1 开始
            ps.setString(2, "drelang");
            ps.setString(3, "$dkdfas9398rtghaskjfklas");
            ps.setString(4, "13156533607");
            ps.setString(5, "541231927123718937");
            ps.setString(6, "1");
            ps.setString(7, "1751175643");
            // 处理执行结果
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 测试查询
    private static ResultSet read() {
        String sql = "select * from user";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
