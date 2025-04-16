/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DisciplineDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MaiTrinh
 */

import DTO.DisciplineDTO;
import java.sql.*;
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
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DisciplineDTO disc = new DisciplineDTO();
                disc.setDisciplineId(rs.getInt("discipline_id"));
                disc.setEmployeeId(rs.getInt("employee_id"));
                disc.setDisciplineType(rs.getString("discipline_type"));
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

    public boolean add(DisciplineDTO disc) {
        String sql = "INSERT INTO discipline (employee_id, discipline_type, discipline_amount, description, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, disc.getEmployeeId());
            stmt.setString(2, disc.getDisciplineType());
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
        String sql = "UPDATE discipline SET employee_id = ?, discipline_type = ?, discipline_amount = ?, description = ?, status = ? WHERE discipline_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, disc.getEmployeeId());
            stmt.setString(2, disc.getDisciplineType());
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

