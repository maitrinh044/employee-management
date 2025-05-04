/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MaiTrinh
 */
import DTO.DisciplineDTO;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {

    private final DatabaseConnect dbConnect;

    public DisciplineDAO() {
        dbConnect = new DatabaseConnect();
    }

    public List<DisciplineDTO> getAll() {
        List<DisciplineDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM discipline WHERE status = true";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisciplineDTO disc = new DisciplineDTO();
                disc.setDisciplineId(rs.getInt("discipline_id"));
                disc.setEmployeeId(rs.getInt("employee_id"));
                disc.setDate(rs.getDate("discipline_date"));
                disc.setDisciplineAmount(rs.getInt("discipline_amount"));
                disc.setDescription(rs.getString("description"));
                disc.setStatus(rs.getBoolean("status"));
                list.add(disc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DisciplineDTO getById(int id) {
        DisciplineDTO dis = new DisciplineDTO();
        String sql = "SELECT * FROM discipline WHERE discipline_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dis.setDisciplineId(rs.getInt("discipline_id"));
                dis.setEmployeeId(rs.getInt("employee_id"));
                dis.setDisciplineAmount(rs.getInt("discipline_amount"));
                dis.setDate(rs.getDate("discipline_date"));
                dis.setDescription(rs.getString("description"));
                dis.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dis;
    }

    public boolean add(DisciplineDTO disc) {
        String sql = "INSERT INTO discipline (employee_id, discipline_date, discipline_amount, description, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, disc.getEmployeeId());
            stmt.setDate(2, disc.getDate());
            stmt.setInt(3, disc.getDisciplineAmount());
            stmt.setString(4, disc.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(DisciplineDTO disc) {
        String sql = "UPDATE discipline SET employee_id = ?, discipline_date = ?, discipline_amount = ?, description = ?, status = ? WHERE discipline_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, disc.getEmployeeId());
            stmt.setDate(2, disc.getDate());
            stmt.setInt(3, disc.getDisciplineAmount());
            stmt.setString(4, disc.getDescription());
            stmt.setBoolean(5, disc.getStatus());
            stmt.setInt(6, disc.getDisciplineId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE discipline SET status = ? WHERE discipline_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lọc kỷ luật theo employeeId và status
    public List<DisciplineDTO> filterByEmployeeIdAndStatus(int employeeId, boolean status) {
        List<DisciplineDTO> disciplines = new ArrayList<>();
        String sql = "SELECT * FROM discipline WHERE employee_id = ? AND status = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setBoolean(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DisciplineDTO discipline = new DisciplineDTO();
                discipline.setDisciplineId(rs.getInt("discipline_id"));
                discipline.setEmployeeId(rs.getInt("employee_id"));
                discipline.setDate(rs.getDate("discipline_type"));
                discipline.setDisciplineAmount(rs.getInt("discipline_amount"));
                discipline.setDescription(rs.getString("description"));
                discipline.setStatus(rs.getBoolean("status"));
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    // Lấy kỷ luật theo khoảng thời gian
    public List<DisciplineDTO> getDisciplinesByDateRange(int employeeId, String startDate, String endDate) {
        List<DisciplineDTO> disciplines = new ArrayList<>();
        String sql = "SELECT * FROM discipline WHERE employee_id = ? AND discipline_date BETWEEN ? AND ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DisciplineDTO discipline = new DisciplineDTO();
                discipline.setDisciplineId(rs.getInt("discipline_id"));
                discipline.setEmployeeId(rs.getInt("employee_id"));
                discipline.setDate(rs.getDate("discipline_type"));
                discipline.setDisciplineAmount(rs.getInt("discipline_amount"));
                discipline.setDescription(rs.getString("description"));
                discipline.setStatus(rs.getBoolean("status"));
                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    // Lấy tổng số kỷ luật của nhân viên trong tháng
    public int getTotalDisciplineForEmployeeInMonth(int employeeId, int month, int year) {
        int totalDiscipline = 0;
        String sql = "SELECT SUM(discipline_amount) AS totalDiscipline "
                + "FROM discipline "
                + "WHERE employee_id = ? AND MONTH(discipline_date) = ? AND YEAR(discipline_date) = ?";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalDiscipline = rs.getInt("totalDiscipline");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalDiscipline;
    }
    
    public List<DisciplineDTO> searchDisByNameAndAmount(String keyword) {
        List<DisciplineDTO> ds = new ArrayList<>();
        String sql = "SELECT d.* FROM discipline d "
                + "JOIN employees e ON d.employee_id = e.employee_id "
                + "WHERE e.full_name LIKE ? OR d.discipline_amount = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            int rwAmount;
            try {
                rwAmount = Integer.parseInt(keyword);
            } catch (NumberFormatException e) {
                rwAmount = -1; // giá trị không khớp nào
            }
            stmt.setInt(2, rwAmount);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DisciplineDTO discipline = new DisciplineDTO();
                discipline.setDisciplineId(rs.getInt("discipline_id"));
                discipline.setEmployeeId(rs.getInt("employee_id"));
                discipline.setDate(rs.getDate("discipline_date"));
                discipline.setDisciplineAmount(rs.getInt("discipline_amount"));
                discipline.setDescription(rs.getString("description"));
                discipline.setStatus(rs.getBoolean("status"));
                ds.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
