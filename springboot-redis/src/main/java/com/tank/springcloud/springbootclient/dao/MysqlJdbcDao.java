package com.tank.springcloud.springbootclient.dao;

import java.sql.*;

public class MysqlJdbcDao {


    private final static String driver = "com.mysql.cj.jdbc.Driver";

    private final static String url = "jdbc:mysql://w-kh-public-sit-mysql.service.testdb:3306/db_sbb3_sit?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final static String userName = "u_sbb3_sit";
    private final static String pwd = "D8ypKxvAo7m8GsYj";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            //驱动注册
            Class.forName(driver);

            //打开链接
            conn = DriverManager.getConnection(url, userName, pwd);

            //执行查询
            statement = conn.createStatement();
            String sql = "select * from ods_sema_verifycode_record osvr  order by id desc limit 10";
            ResultSet resultSet = statement.executeQuery(sql);
            //输出结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phone_number = resultSet.getString("phone_number");
                String sms_content = resultSet.getString("sms_content");
                System.out.println("id=" + id + " phone_number:" + phone_number + " sms_content:" + sms_content);
            }
            resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("happenen exception" + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
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
            System.out.println("execute end ...");
        }
    }
}
