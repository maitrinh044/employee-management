/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.PositionDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO {

    private final DatabaseConnect dbConnect;

    public PositionDAO() {
        dbConnect = new DatabaseConnect();
    }

    // Lấy tất cả các vị trí
    public List<PositionDTO> getAllPositions() {
        List<PositionDTO> positionList = new ArrayList<>();
        String sql = "SELECT * FROM positions";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PositionDTO position = new PositionDTO();
                position.setPositionId(rs.getInt("position_id"));
                position.setPositionName(rs.getString("position_name"));
                position.setBaseSalary(rs.getInt("base_salary"));
                position.setStatus(rs.getBoolean("status"));
                positionList.add(position);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positionList;
    }

    // Lấy vị trí theo ID
    public PositionDTO getPositionById(int positionId) {
        PositionDTO position = null;
        String sql = "SELECT * FROM positions WHERE position_id = ?";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, positionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                position = new PositionDTO();
                position.setPositionId(rs.getInt("position_id"));
                position.setPositionName(rs.getString("position_name"));
                position.setBaseSalary(rs.getInt("base_salary"));
                position.setStatus(rs.getBoolean("status"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return position;
    }

    // Cập nhật thông tin vị trí
    public boolean updatePosition(PositionDTO position) {
        String sql = "UPDATE positions SET position_name = ?, base_salary = ?, status = ? WHERE position_id = ?";
        boolean success = false;

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, position.getPositionName());
            stmt.setInt(2, position.getBaseSalary());
            stmt.setBoolean(3, position.isStatus());
            stmt.setInt(4, position.getPositionId());

            success = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // Thêm mới vị trí
    public boolean addPosition(PositionDTO position) {
        String sql = "INSERT INTO positions (position_name, base_salary, status) VALUES (?, ?, ?)";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, position.getPositionName());
            stmt.setInt(2, position.getBaseSalary());
            stmt.setBoolean(3, position.isStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật trạng thái vị trí
    public boolean updatePositionStatus(int positionId, boolean status) {
        String sql = "UPDATE positions SET status = ? WHERE position_id = ?";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, status);
            stmt.setInt(2, positionId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Tìm kiếm vị trí theo tên
    public List<PositionDTO> search(String keyword) {
        List<PositionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM positions WHERE position_name LIKE ? AND status = true";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PositionDTO position = new PositionDTO();
                position.setPositionId(rs.getInt("position_id"));
                position.setPositionName(rs.getString("position_name"));
                position.setBaseSalary(rs.getInt("base_salary"));
                position.setStatus(rs.getBoolean("status"));
                list.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy thông tin vị trí của nhân viên theo employeeId
    public PositionDTO getPositionByEmployeeId(int employeeId) {
        PositionDTO position = null;
        String sql = "SELECT p.position_id, p.position_name, p.base_salary, p.status " +
                     "FROM positions p " +
                     "JOIN employees e ON p.position_id = e.position_id " +
                     "WHERE e.employee_id = ?";
        
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    position = new PositionDTO();
                    position.setPositionId(rs.getInt("position_id"));
                    position.setPositionName(rs.getString("position_name"));
                    position.setBaseSalary(rs.getInt("base_salary"));
                    position.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return position;
    }

    public PositionDTO getPositionByName(String name) {
        PositionDTO position = null;
        String sql = "SELECT * FROM positions WHERE position_name = ?";
        
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    position = new PositionDTO();
                    position.setPositionId(rs.getInt("position_id"));
                    position.setPositionName(rs.getString("position_name"));
                    position.setBaseSalary(rs.getInt("base_salary"));
                    position.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return position;
    }
}

