/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.ProjectDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private final DatabaseConnect db = new DatabaseConnect();

    public List<ProjectDTO> getAll() {
        List<ProjectDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM project WHERE status = true";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProjectDTO p = new ProjectDTO();
                p.setProjectId(rs.getInt("project_id"));
                p.setProjectName(rs.getString("project_name"));
                p.setManagerId(rs.getInt("manager_id"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(ProjectDTO p) {
        String sql = "INSERT INTO project (project_name, description, status) VALUES (?, ?, true)";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getProjectName());
            stmt.setInt(2, p.getManagerId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(ProjectDTO project) {
        String sql = "UPDATE project SET project_name = ?, start_date = ?, end_date = ?, manager_id = ?, status = ? WHERE project_id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, project.getProjectName());
            stmt.setString(2, project.getStartDate());
            stmt.setString(3, project.getEndDate());
            stmt.setInt(4, project.getManagerId());
            stmt.setBoolean(5, project.getStatus());
            stmt.setInt(6, project.getProjectId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE project SET status = ? WHERE project_id = ?";
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
}

