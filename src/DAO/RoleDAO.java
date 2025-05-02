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

public class RoleDAO {

    private final DatabaseConnect dbConnect;

    public RoleDAO() {
        dbConnect = new DatabaseConnect();
    }

    public String getRoleNameById(int roleId) {
        String roleName = "";
        String sql = "SELECT role_name FROM roles WHERE role_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                roleName = rs.getString("role_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }

}
