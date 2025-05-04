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

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getString("phone_number"));
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

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getString("phone_number"));
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

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFullName());
            stmt.setDate(2, employee.getBirthday());
            stmt.setString(3, employee.getGender());
            stmt.setString(4, employee.getPhoneNumber());
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

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFullName());
            stmt.setDate(2, employee.getBirthday());
            stmt.setString(3, employee.getGender());
            stmt.setString(4, employee.getPhoneNumber());
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

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM employees WHERE full_name LIKE ? OR phone_number LIKE ? OR address LIKE ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setAddress(rs.getString("address"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setStatus(rs.getBoolean("status"));
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy nhân viên chưa có tài khoản
    public List<EmployeeDTO> getEmplNotAccount() {
        List<EmployeeDTO> list = new ArrayList<>();
        // Câu truy vấn JOIN với bảng `account` để lấy nhân viên chưa có tài khoản
        String sql = "SELECT e.* "
                + "FROM employees e "
                + "LEFT JOIN accounts a ON e.employee_id = a.employee_id "
                + "WHERE a.employee_id IS NULL AND e.status = TRUE";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeDTO emp = new EmployeeDTO();

                // Lấy dữ liệu từ ResultSet và gán vào đối tượng EmployeeDTO
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFullName(rs.getString("full_name"));
                emp.setBirthday(rs.getDate("birthday")); // Bạn có thể xử lý kiểu dữ liệu này nếu cần
                emp.setGender(rs.getString("gender"));
                emp.setPhoneNumber(rs.getString("phone_number"));
                emp.setAddress(rs.getString("address"));
                emp.setPositionId(rs.getInt("position_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setStatus(rs.getBoolean("status"));

                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<EmployeeDTO> getAllEmployeeRole() {
        List<EmployeeDTO> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE position_id = 3";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getString("phone_number"));
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

    public boolean isExistPhoneNumber(String phone) {
        String sql = "SELECT * FROM employees WHERE phone_number = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<EmployeeDTO> getEmployeesNotInAnyProject() {
        List<EmployeeDTO> list = new ArrayList<>();
        String sql = "SELECT e.* FROM employees e LEFT JOIN project_employee ep ON e.employee_id = ep.employee_id WHERE ep.project_id IS NULL";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setGender(rs.getString("gender"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setAddress(rs.getString("address"));
                employee.setPositionId(rs.getInt("position_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setStatus(rs.getBoolean("status"));
                list.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
