package com.wzres.utils;

import java.sql.*;

/**
 * @ClassName DBUtil
 * @description JDBC封装工具类
 * @date 2023-06-11 21:26
 */
public class DBUtil {

    /*
    工具类中的构造方法都是私有的。
    因为工具类当中的方法都是静态的，不需要new对象，直接采用类名调用。
    */

    private DBUtil(){
    }

    //静态代码块在类加载时执行，并且只执行一次。
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * 获取连接对象
     * @param
     * @return Connection
     */
    public static Connection getConnection() throws SQLException {
        String url="jdbc:mysql://localhost:3306/wzdemo";
        String user="root";
        String password="9780619";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 查询结果集对象
     * @return void
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
