/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.SalaryDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    private final DatabaseConnect dbConnect;

    public SalaryDAO() {
        dbConnect = new DatabaseConnect();
    }

    public List<SalaryDTO> getAll() {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM salary WHERE status = true";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SalaryDTO s = new SalaryDTO();
                s.setSalaryId(rs.getInt("salary_id"));
                s.setEmployeeId(rs.getInt("employee_id"));
                s.setMonth(rs.getInt("month"));
                s.setYear(rs.getInt("year"));
                s.setSalaryAmount(rs.getInt("total_salary"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(SalaryDTO s) {
        String sql = "INSERT INTO salary (employee_id, month, year, total_salary, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getEmployeeId());
            stmt.setInt(2, s.getMonth());
            stmt.setInt(3, s.getYear());
            stmt.setInt(4, s.getSalaryAmount());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(SalaryDTO s) {
        String sql = "UPDATE salary SET employee_id = ?, month = ?, year = ?, total_salary = ?, status = ? WHERE salary_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getEmployeeId());
            stmt.setInt(2, s.getMonth());
            stmt.setInt(3, s.getYear());
            stmt.setInt(4, s.getSalaryAmount());
            stmt.setBoolean(5, s.getStatus());
            stmt.setInt(6, s.getSalaryId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE salary SET status = ? WHERE salary_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
