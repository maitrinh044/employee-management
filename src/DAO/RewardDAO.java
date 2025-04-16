/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.RewardDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RewardDAO {
    private final DatabaseConnect db = new DatabaseConnect();

    // Lấy toàn bộ bản ghi có status = true
    public List<RewardDTO> getAll() {
        List<RewardDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM reward WHERE status = true";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RewardDTO r = new RewardDTO();
                r.setRewardId(rs.getInt("reward_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setRewardDate(rs.getString("reward_date"));
                r.setRewardValue(rs.getInt("reward_value"));
                r.setDescription(rs.getString("description"));
                r.setStatus(rs.getBoolean("status"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm mới phần thưởng
    public boolean add(RewardDTO r) {
        String sql = "INSERT INTO reward (employee_id, reward_date, reward_value, description, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getEmployeeId());
            stmt.setString(2, r.getRewardDate());
            stmt.setInt(3, r.getRewardValue());
            stmt.setString(4, r.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật trạng thái (ẩn/xóa mềm)
    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE reward SET status = ? WHERE reward_id = ?";
        try (Connection conn = db.getConnection();
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

    // Cập nhật thông tin phần thưởng
    public boolean update(RewardDTO r) {
        String sql = "UPDATE reward SET employee_id = ?, reward_date = ?, reward_value = ?, description = ?, status = ? WHERE reward_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getEmployeeId());
            stmt.setString(2, r.getRewardDate());
            stmt.setInt(3, r.getRewardValue());
            stmt.setString(4, r.getDescription());
            stmt.setBoolean(5, r.getStatus());
            stmt.setInt(6, r.getRewardId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
