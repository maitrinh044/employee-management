/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.DepartmentDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private final DatabaseConnect db;

    public DepartmentDAO() {
        db = new DatabaseConnect();
    }

    // 1. Lấy tất cả phòng ban
    public List<DepartmentDTO> getAll() {
        List<DepartmentDTO> list = new ArrayList<>();
        String query = "SELECT * FROM departments";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DepartmentDTO dept = new DepartmentDTO();
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
                dept.setManagerId(rs.getInt("manager_id"));
                dept.setStatus(rs.getBoolean("status"));
                list.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Thêm phòng ban mới
    public boolean add(DepartmentDTO dept) {
        String query = "INSERT INTO departments (department_name, manager_id, status) VALUES (?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dept.getDepartmentName());
            stmt.setInt(2, dept.getManagerId());
            stmt.setBoolean(3, dept.isStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Cập nhật thông tin phòng ban
    public boolean update(DepartmentDTO dept) {
        String query = "UPDATE departments SET department_name = ?, manager_id = ?, status = ? WHERE department_id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dept.getDepartmentName());
            stmt.setInt(2, dept.getManagerId());
            stmt.setBoolean(3, dept.isStatus());
            stmt.setInt(4, dept.getDepartmentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Cập nhật trạng thái (ẩn/hiện)
    public boolean updateStatus(int departmentId, boolean status) {
        String query = "UPDATE departments SET status = ? WHERE department_id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, status);
            stmt.setInt(2, departmentId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Lấy theo ID
    public DepartmentDTO getById(int id) {
        String query = "SELECT * FROM departments WHERE department_id = ?";
        DepartmentDTO dept = null;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dept = new DepartmentDTO();
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
                dept.setManagerId(rs.getInt("manager_id"));
                dept.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dept;
    }
   
}
