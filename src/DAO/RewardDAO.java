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
        String sql = "SELECT * FROM rewards";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RewardDTO r = new RewardDTO();
                r.setRewardId(rs.getInt("reward_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setRewardDate(rs.getDate("reward_date"));
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

    public RewardDTO getById(int id) {
        RewardDTO r = new RewardDTO();
        String sql = "SELECT * FROM rewards WHERE reward_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                r.setRewardId(rs.getInt("reward_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setRewardDate(rs.getDate("reward_date"));
                r.setRewardValue(rs.getInt("reward_value"));
                r.setDescription(rs.getString("description"));
                r.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    // Thêm mới phần thưởng
    public boolean add(RewardDTO r) {
        String sql = "INSERT INTO rewards (employee_id, reward_date, reward_value, description, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getEmployeeId());
            stmt.setDate(2, r.getRewardDate());
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
        String sql = "UPDATE rewards SET status = ? WHERE reward_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "UPDATE rewards SET employee_id = ?, reward_date = ?, reward_value = ?, description = ?, status = ? WHERE reward_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getEmployeeId());
            stmt.setDate(2, r.getRewardDate());
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

    // Lọc phần thưởng theo employeeId và status
    public List<RewardDTO> filterByEmployeeIdAndStatus(int employeeId, boolean status) {
        List<RewardDTO> rewards = new ArrayList<>();
        String sql = "SELECT * FROM rewards WHERE employee_id = ? AND status = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setBoolean(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RewardDTO reward = new RewardDTO();
                reward.setRewardId(rs.getInt("reward_id"));
                reward.setEmployeeId(rs.getInt("employee_id"));
                reward.setRewardDate(rs.getDate("reward_date"));
                reward.setRewardValue(rs.getInt("reward_value"));
                reward.setDescription(rs.getString("description"));
                reward.setStatus(rs.getBoolean("status"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewards;
    }

    // Lấy thưởng theo khoảng thời gian
    public List<RewardDTO> getRewardsByDateRange(int employeeId, String startDate, String endDate) {
        List<RewardDTO> rewards = new ArrayList<>();
        String sql = "SELECT * FROM rewards WHERE employee_id = ? AND reward_date BETWEEN ? AND ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RewardDTO reward = new RewardDTO();
                reward.setRewardId(rs.getInt("reward_id"));
                reward.setEmployeeId(rs.getInt("employee_id"));
                reward.setRewardDate(rs.getDate("reward_date"));
                reward.setRewardValue(rs.getInt("reward_value"));
                reward.setDescription(rs.getString("description"));
                reward.setStatus(rs.getBoolean("status"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewards;
    }

    // Lấy tổng số thưởng của nhân viên trong tháng
    public int getTotalRewardForEmployeeInMonth(int employeeId, int month, int year) {
        int totalReward = 0;
        String sql = "SELECT SUM(reward_value) AS totalReward "
                + "FROM rewards "
                + "WHERE employee_id = ? AND MONTH(reward_date) = ? AND YEAR(reward_date) = ?";

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalReward = rs.getInt("totalReward");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalReward;
    }

    public List<RewardDTO> searchByNameAndAmount(String keyword) {
        List<RewardDTO> rewards = new ArrayList<>();
        String sql = "SELECT r.* FROM rewards r "
                + "JOIN employees e ON r.employee_id = e.employee_id "
                + "WHERE e.full_name LIKE ? OR r.reward_value = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
                RewardDTO reward = new RewardDTO();
                reward.setRewardId(rs.getInt("reward_id"));
                reward.setEmployeeId(rs.getInt("employee_id"));
                reward.setRewardDate(rs.getDate("reward_date"));
                reward.setRewardValue(rs.getInt("reward_value"));
                reward.setDescription(rs.getString("description"));
                reward.setStatus(rs.getBoolean("status"));
                rewards.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewards;
    }
}
