package com.bupt.awesomejava.scaffold.dbconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
    private static final Logger log = LoggerFactory.getLogger(DBConnection.class.getName());
    private static final String dbUrl = "jdbc:sqlserver://10.237.1.250:1433;selectMethod=cursor;";
    private static final String dbUserName = "sa";
    private static final String dbPassword = "c85130268Sc_";
    private static final String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JDBCconnection();
    }

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void JDBCconnection() throws ClassNotFoundException, SQLException {
            // 1. 加载驱动类
            Class.forName(jdbcName);
            Connection conn = null;
            Statement stat = null;
            ResultSet rs = null;
        try {
            // 2. 创建连接
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            // 3. 编写sql
            String sql = "select top 10 * from crmsys.dbo.t_sys_user;";
            // 4. 创建PreparedStatement
            stat = conn.createStatement();
            // 5. 获取查询结果
            rs = stat.executeQuery(sql);
            Map<String, String> resultMap = new HashMap<>();
            while (rs.next()) {
                String id = rs.getString("user_id");
                String name = rs.getString("user_name");
                resultMap.put(id, name);
            }
            System.out.println(resultMap.toString());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            log.info("查询成功");
            if(rs != null) {
                rs.close();
            }
            if(stat != null) {
                stat.close();
            }
            if(conn != null) {
                conn.close();
            }
        }
    }
    /**
     * 数据库连接池
     */
}
