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
import javax.swing.JOptionPane;

public class DatabaseConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DBName = "employee_management";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    public DatabaseConnect() {
        checkDriver();
        setupConnect();
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể kết nối tới cơ sở dữ liệu\n" + e.getMessage());
            return null;
        }
    }

    private void setupConnect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("-- ERROR! Không thể kết nối tới cơ sở dữ liệu");
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể kết nối tới cơ sở dữ liệu");
        }
    }

    public Boolean checkConnect() {
        if (conn == null) {
            JOptionPane.showMessageDialog(null,
                    "-- ERROR! Chưa thiết lập kết nối tới cơ sở dữ liệu. Vui lòng đăng nhập để thiết lập kết nối!");
            return false;
        }
        return true;
    }

    public void closeConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "-- ERROR! Không thể đóng kết nối tới cơ sở dữ liệu\n" + ex.getLocalizedMessage());
        }
    }

    private void checkDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không tìm thấy Driver mySql");
        }
    }

    public ResultSet sqlQuery(String qry) {
        if (checkConnect()) {
            try {
                pstmt = conn.prepareStatement(qry);
                rset = pstmt.executeQuery();
                return rset;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "-- ERROR! Không thể lấy dữ liệu từ " + DBName + "\n" + ex.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }

    public Boolean sqlUpdate(String qry) {
        if (checkConnect()) {
            try {
                pstmt = conn.prepareStatement(qry);
                pstmt.executeUpdate();
                return true;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "-- ERROR! Không thể ghi dữ liệu xuống " + DBName + "\n" + ex.getLocalizedMessage());
                return false;
            }
        }
        return false;
    }

}
