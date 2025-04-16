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
import java.sql.ResultSet;
import java.util.ArrayList;

public class PositionDAO {
    DatabaseConnect db = new DatabaseConnect();

    public ArrayList<PositionDTO> getAll() {
        ArrayList<PositionDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM positions WHERE status = true";
            ResultSet rs = db.sqlQuery(sql);
            while (rs.next()) {
                PositionDTO p = new PositionDTO(
                    rs.getInt("position_id"),
                    rs.getString("position_name"),
                    rs.getInt("base_salary"),
                    rs.getBoolean("status")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(PositionDTO p) {
        String sql = "INSERT INTO positions (position_name, base_salary, status) VALUES (?, ?, true)";
        try {
            db.pstmt = db.getConnection().prepareStatement(sql);
            db.pstmt.setString(1, p.getPositionName());
            db.pstmt.setInt(2, p.getBaseSalary());
            db.pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(PositionDTO p) {
        String sql = "UPDATE positions SET position_name = ?, base_salary = ?, status = ? WHERE position_id = ?";
        try {
            db.pstmt = db.getConnection().prepareStatement(sql);
            db.pstmt.setString(1, p.getPositionName());
            db.pstmt.setInt(2, p.getBaseSalary());
            db.pstmt.setBoolean(3, p.isStatus());
            db.pstmt.setInt(4, p.getPositionId());
            db.pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE positions SET status = ? WHERE position_id = ?";
        try {
            db.pstmt = db.getConnection().prepareStatement(sql);
            db.pstmt.setBoolean(1, status);
            db.pstmt.setInt(2, id);
            db.pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
