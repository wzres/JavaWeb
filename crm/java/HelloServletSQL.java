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

        //��дJDBC���룬�������ݿ⣬��ѯ����ѧ����Ϣ��
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            //ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //��ȡ����
            String url = "jdbc:mysql://localhost:3306/wzdemo";
            String user = "root";
            String password = "9780619";
            conn =  DriverManager.getConnection(url, user, password);
            //��ȡԤ��������ݿ��������
            String sql = "select username,age,address from t_user";
            System.out.println("���ӳɹ�"+conn);
            ps = conn.prepareStatement(sql);
            //ִ��SQL
            rs = ps.executeQuery();
            //�����ѯ�ṹ��
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

        out.print("���ʳɹ�");

    }
}
