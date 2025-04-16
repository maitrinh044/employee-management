/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */

import DTO.AccountDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDAO {
    DatabaseConnect db = new DatabaseConnect();

    public ArrayList<AccountDTO> getAll() {
        ArrayList<AccountDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM accounts WHERE status = true";
            ResultSet rs = db.sqlQuery(sql);
            while (rs.next()) {
                AccountDTO a = new AccountDTO(
                    rs.getInt("account_id"),
                    rs.getInt("employee_id"),
                    rs.getString("username"),
                    rs.getInt("role_id"),
                    rs.getTimestamp("created_at"),
                    rs.getBoolean("status")
                );
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(AccountDTO a) {
        String sql = "INSERT INTO accounts (employee_id, username, role_id, created_at, status) VALUES (?, ?, ?, NOW(), true)";
        try {
            db.pstmt = db.getConnection().prepareStatement(sql);
            db.pstmt.setInt(1, a.getEmployeeId());
            db.pstmt.setString(2, a.getUsername());
            db.pstmt.setInt(3, a.getRoleId());
            db.pstmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE accounts SET status = ? WHERE account_id = ?";
        try {
            db.pstmt = db.getConnection().prepareStatement(sql);
            db.pstmt.setBoolean(1, status);
            db.pstmt.setInt(2, id);
            db.pstmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

