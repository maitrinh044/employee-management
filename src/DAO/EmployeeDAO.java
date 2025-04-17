/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */
import DTO.EmployeeDTO;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class EmployeeDAO {

    private final DatabaseConnect dbConnect;

    public EmployeeDAO() {
        dbConnect = new DatabaseConnect();
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getString("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getLong("phone_number"));
                employee.setAddress(rs.getString("address"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setStatus(rs.getBoolean("status"));
                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    // Lấy nhân viên theo ID
    public EmployeeDTO getEmployeeById(int id) {
        EmployeeDTO employee = null;
        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getString("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getLong("phone_number"));
                employee.setAddress(rs.getString("address"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setStatus(rs.getBoolean("status"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    // Cập nhật thông tin nhân viên
    public boolean updateEmployee(EmployeeDTO employee) {
        String sql = "UPDATE employees SET full_name = ?, birthday = ?, gender = ?, phone_number = ?, address = ?, position_id = ?, department_id = ?, status = ? WHERE employee_id = ?";
        boolean success = false;

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getBirthday());
            stmt.setString(3, employee.getGender());
            stmt.setLong(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getAddress());
            stmt.setInt(6, employee.getPositionId());
            stmt.setInt(7, employee.getDepartmentId());
            stmt.setBoolean(8, employee.isStatus());
            stmt.setInt(9, employee.getEmployeeId());

            success = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
    public boolean addEmployee(EmployeeDTO employee) {
        String sql = "INSERT INTO employees (full_name, birthday, gender, phone_number, address, position_id, department_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFullName());
            stmt.setString(2, employee.getBirthday());
            stmt.setString(3, employee.getGender());
            stmt.setLong(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getAddress());
            stmt.setInt(6, employee.getPositionId());
            stmt.setInt(7, employee.getDepartmentId());
            stmt.setBoolean(8, employee.isStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean updateEmployeeStatus(int employeeId, boolean status) {
        String sql = "UPDATE employees SET status = ? WHERE employee_id = ?";

        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, status);
            stmt.setInt(2, employeeId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<EmployeeDTO> search(String keyword) {
        List<EmployeeDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE status = true AND (name LIKE ? OR position_id LIKE ?)";
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFullName(rs.getString("name"));
                emp.setPositionId(rs.getInt("position_id"));
                emp.setStatus(rs.getBoolean("status"));
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

