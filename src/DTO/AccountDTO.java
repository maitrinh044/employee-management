/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
import java.sql.Timestamp;

public class AccountDTO {
    private int accountId;
    private int employeeId;
    private String username;
    private String password;
    private int roleId;
    private Timestamp createdAt;
    private boolean status;

    public AccountDTO() {
    }

    public AccountDTO(int accountId, int employeeId, String username, String password, int roleId, Timestamp createdAt, boolean status) {
        this.accountId = accountId;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.status = status;
    }
    
    public AccountDTO(AccountDTO accountDTO) {
        this.accountId = accountDTO.accountId;
        this.employeeId = accountDTO.employeeId;
        this.username = accountDTO.username;
        this.password = accountDTO.password;
        this.roleId = accountDTO.roleId;
        this.createdAt = accountDTO.createdAt;
        this.status = accountDTO.status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", employeeId=" + employeeId +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
