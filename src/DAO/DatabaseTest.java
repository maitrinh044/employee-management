/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */
import java.sql.*;

public class DatabaseTest {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Nạp driver JDBC cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Kết nối đến MySQL database
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
            
            // Đóng kết nối
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp driver MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối tới cơ sở dữ liệu.");
            e.printStackTrace();
        }
    }
}

