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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private final DatabaseConnect dbConnect;

    public AccountDAO() {
        dbConnect = new DatabaseConnect();
    }

    // Lấy tất cả tài khoản
    public List<AccountDTO> getAll() {
        List<AccountDTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE status = true";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setEmployeeId(rs.getInt("employee_id"));
                account.setUsername(rs.getString("username"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedAt(rs.getTimestamp("created_at"));
                account.setStatus(rs.getBoolean("status"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Thêm tài khoản mới
    public boolean add(AccountDTO account) {
        String sql = "INSERT INTO accounts (employee_id, username, role_id, created_at, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, account.getEmployeeId());
            stmt.setString(2, account.getUsername());
            stmt.setInt(3, account.getRoleId());
            stmt.setTimestamp(4, account.getCreatedAt());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật tài khoản
    public boolean update(AccountDTO account) {
        String sql = "UPDATE accounts SET username = ?, role_id = ?, created_at = ? WHERE account_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getUsername());
            stmt.setInt(2, account.getRoleId());
            stmt.setTimestamp(3, account.getCreatedAt());
            stmt.setInt(4, account.getAccountId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật trạng thái tài khoản
    public boolean updateStatus(int accountId, boolean status) {
        String sql = "UPDATE accounts SET status = ? WHERE account_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm tài khoản theo username
    public AccountDTO getByUsername(String username) {
        AccountDTO account = null;
        String sql = "SELECT * FROM accounts WHERE username = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setEmployeeId(rs.getInt("employee_id"));
                account.setUsername(rs.getString("username"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedAt(rs.getTimestamp("created_at"));
                account.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Lọc tài khoản theo roleId
    public List<AccountDTO> getByRole(int roleId) {
        List<AccountDTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE role_id = ? AND status = true";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setEmployeeId(rs.getInt("employee_id"));
                account.setUsername(rs.getString("username"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedAt(rs.getTimestamp("created_at"));
                account.setStatus(rs.getBoolean("status"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Lấy tài khoản theo employeeId
    public AccountDTO getByEmployeeId(int employeeId) {
        AccountDTO account = null;
        String sql = "SELECT * FROM accounts WHERE employee_id = ?";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                account = new AccountDTO();
                account.setAccountId(rs.getInt("account_id"));
                account.setEmployeeId(rs.getInt("employee_id"));
                account.setUsername(rs.getString("username"));
                account.setRoleId(rs.getInt("role_id"));
                account.setCreatedAt(rs.getTimestamp("created_at"));
                account.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
