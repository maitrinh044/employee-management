/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author duyen
 */
import DTO.ProjectDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProjectDAO {

    private final DatabaseConnect db = new DatabaseConnect();

    public ProjectDTO getProjectById(int projectId) {
        ProjectDTO project = null;
        String sql = "SELECT project_id, project_name, start_date, end_date, manager_id, status FROM projects WHERE project_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                project = new ProjectDTO();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setStartDate(rs.getDate("start_date"));
                project.setEndDate(rs.getDate("end_date"));
                project.setManagerId(rs.getInt("manager_id"));
                project.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin dự án theo ID\n" + e.getMessage());
        }
        return project;
    }

    public List<ProjectDTO> getAll() {
        List<ProjectDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        DatabaseConnect db = new DatabaseConnect();
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (rs.next()) {
                ProjectDTO p = new ProjectDTO();
                p.setProjectId(rs.getInt("project_id"));
                p.setProjectName(rs.getString("project_name"));
                p.setStartDate(rs.getDate("start_date"));
                p.setEndDate(rs.getDate("end_date"));
                p.setManagerId(rs.getInt("manager_id"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean add(ProjectDTO p) {
        String sql = "INSERT INTO projects (project_name, start_date, end_date, manager_id, status) VALUES (?, ?, ?, ?, ?)";
        // Lưu ý: Không có cột project_id trong danh sách các cột và giá trị
        System.out.println("Câu lệnh SQL INSERT: " + sql);
        DatabaseConnect db = new DatabaseConnect();
        try (Connection conn = db.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getProjectName());
            pstmt.setDate(2, p.getStartDate());
            pstmt.setDate(3, p.getEndDate());
            pstmt.setInt(4, p.getManagerId());
            pstmt.setBoolean(5, p.getStatus());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể thêm dự án\n" + e.getMessage());
            return false;
        }
    }

    public boolean update(ProjectDTO project) {
        String sql = "UPDATE projects SET project_name = ?, start_date = ?, end_date = ?, manager_id = ?, status = ? WHERE project_id = ?";

        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getProjectName());
            stmt.setDate(2, project.getStartDate());
            stmt.setDate(3, project.getEndDate());
            stmt.setInt(4, project.getManagerId());
            stmt.setBoolean(5, project.getStatus());
            stmt.setInt(6, project.getProjectId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông tin chi tiết về lỗi (stack trace)
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật dự án\n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE projects SET status = ? WHERE project_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

}
