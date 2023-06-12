package com.wzres.utils;

import java.sql.*;

/**
 * @ClassName DBUtil
 * @description JDBC��װ������
 * @date 2023-06-11 21:26
 */
public class DBUtil {

    /*
    �������еĹ��췽������˽�еġ�
    ��Ϊ�����൱�еķ������Ǿ�̬�ģ�����Ҫnew����ֱ�Ӳ����������á�
    */

    private DBUtil(){
    }

    //��̬������������ʱִ�У�����ִֻ��һ�Ρ�
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * ��ȡ���Ӷ���
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
     * �ر���Դ
     * @param conn ���Ӷ���
     * @param ps ���ݿ��������
     * @param rs ��ѯ���������
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
