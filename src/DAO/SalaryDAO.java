/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MaiTrinh
 */
import DTO.SalaryDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {

    private final DatabaseConnect dbConnect;

    public SalaryDAO() {
        dbConnect = new DatabaseConnect();
    }

    public List<SalaryDTO> getAll() {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM salarys WHERE status = true";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SalaryDTO s = new SalaryDTO();
                s.setSalaryId(rs.getInt("salary_id"));
                s.setEmployeeId(rs.getInt("employee_id"));
                s.setMonth(rs.getInt("month"));
                s.setYear(rs.getInt("year"));
                s.setSalaryAmount(rs.getInt("salary_amount"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public SalaryDTO getById(int id) {
        SalaryDTO s = new SalaryDTO();
        String sql = "SELECT * FROM salarys WHERE salary_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                s.setSalaryId(rs.getInt("salary_id"));
                s.setEmployeeId(rs.getInt("employee_id"));
                s.setMonth(rs.getInt("month"));
                s.setYear(rs.getInt("year"));
                s.setSalaryAmount(rs.getInt("salary_amount"));
                s.setStatus(rs.getBoolean("status"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean add(SalaryDTO s) {
        String sql = "INSERT INTO salarys (employee_id, month, year, salary_amount, status) VALUES (?, ?, ?, ?, true)";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getEmployeeId());
            stmt.setInt(2, s.getMonth());
            stmt.setInt(3, s.getYear());
            stmt.setInt(4, s.getSalaryAmount());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(SalaryDTO s) {
        String sql = "UPDATE salarys SET employee_id = ?, month = ?, year = ?, salary_amount = ?, status = ? WHERE salary_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getEmployeeId());
            stmt.setInt(2, s.getMonth());
            stmt.setInt(3, s.getYear());
            stmt.setInt(4, s.getSalaryAmount());
            stmt.setBoolean(5, s.getStatus());
            stmt.setInt(6, s.getSalaryId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, boolean status) {
        String sql = "UPDATE salary SET status = ? WHERE salary_id = ?";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SalaryDTO> getByEmployeeId(int employeeId) {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM salarys WHERE employee_id = ? AND status = true";
        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setSalaryAmount(rs.getInt("salary_amount"));
                salary.setMonth(rs.getInt("month"));
                salary.setYear(rs.getInt("year"));
                salary.setStatus(rs.getBoolean("status"));

                list.add(salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SalaryDTO> getByMonthYear(int month, int year) {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM salarys WHERE month = ? AND year = ? AND status = true";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setSalaryAmount(rs.getInt("salary_amount"));
                salary.setMonth(rs.getInt("month"));
                salary.setYear(rs.getInt("year"));
                salary.setStatus(rs.getBoolean("status"));

                list.add(salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SalaryDTO> getByEmployeeAndMonthYear(int employeeId, int month, int year) {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM salarys WHERE employee_id = ? AND month = ? AND year = ? AND status = true";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setSalaryAmount(rs.getInt("salary_amount"));
                salary.setMonth(rs.getInt("month"));
                salary.setYear(rs.getInt("year"));
                salary.setStatus(rs.getBoolean("status"));

                list.add(salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Integer> getAllYear() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT year AS year FROM salarys ORDER BY year DESC";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("year"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SalaryDTO> searchByNameAndAmount(String keyword) {
        List<SalaryDTO> list = new ArrayList<>();
        String sql = "SELECT s.* FROM salarys s "
                + "JOIN employees e ON s.employee_id = e.employee_id "
                + "WHERE e.full_name LIKE ? OR s.salary_amount = ?";

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            int salaryAmount;
            try {
                salaryAmount = Integer.parseInt(keyword);
            } catch (NumberFormatException e) {
                salaryAmount = -1; // giá trị không khớp nào
            }
            stmt.setInt(2, salaryAmount);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setSalaryAmount(rs.getInt("salary_amount"));
                salary.setMonth(rs.getInt("month"));
                salary.setYear(rs.getInt("year"));
                salary.setStatus(rs.getBoolean("status"));
                list.add(salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<SalaryDTO> getTop5HighestSalariesLastMonth() {
        List<SalaryDTO> topSalaries = new ArrayList<>();

        // Tính toán tháng và năm trước
        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        int lastMonthValue = lastMonth.getMonthValue(); // 1-12
        int lastYearValue = lastMonth.getYear();

        String sql = """
                        SELECT s.*
                        FROM salarys s
                        WHERE s.month = ? AND s.year = ?
                        ORDER BY s.salary_amount DESC
                        LIMIT 5
                    """;

        try (Connection conn = dbConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lastMonthValue);
            stmt.setInt(2, lastYearValue);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalaryDTO salary = new SalaryDTO();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setSalaryAmount(rs.getInt("salary_amount"));
                salary.setMonth(rs.getInt("month"));
                salary.setYear(rs.getInt("year"));
                salary.setStatus(rs.getBoolean("status"));
                topSalaries.add(salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return topSalaries;
    }

}
