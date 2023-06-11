package com.wzres.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @ClassName HelloServlet
 * @description
 * @date 2023-06-11 00:10
 */
public class HelloServletSQL extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //编写JDBC代码，连接数据库，查询所有学生信息。
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/wzdemo";
            String user = "root";
            String password = "9780619";
            conn =  DriverManager.getConnection(url, user, password);
            //获取预编译的数据库操作对象
            String sql = "select username,age,address from t_user";
            System.out.println("连接成功"+conn);
            ps = conn.prepareStatement(sql);
            //执行SQL
            rs = ps.executeQuery();
            //处理查询结构集
            while (rs.next()){
                String username = rs.getString("username");
                int anInt = rs.getInt("age");
                String address = rs.getString("address");
                System.out.println(username+","+anInt+","+address);
                out.print(username+","+anInt+","+address+"<br>");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        out.print("访问成功");

    }
}
