/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */
import DAO.EmployeeDAO;
import DTO.EmployeeDTO;
import java.util.List;

public class EmployeeBUS {

    private final EmployeeDAO employeeDAO;

    public EmployeeBUS() {
        employeeDAO = new EmployeeDAO();
    }

    // Lấy danh sách nhân viên đang hoạt động
    public List<EmployeeDTO> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public List<EmployeeDTO> getAllEmployeeRole() {
        return employeeDAO.getAllEmployeeRole();
    }

    // Thêm nhân viên
    public boolean addEmployee(EmployeeDTO emp) {
        if (emp == null || emp.getFullName() == null || emp.getFullName().trim().isEmpty()) {
            System.out.println("Thông tin nhân viên không hợp lệ");
            return false;
        }
        return employeeDAO.addEmployee(emp);
    }

    public boolean updateEmployee(EmployeeDTO emp) {
        if (emp == null || emp.getEmployeeId() <= 0) {
            System.out.println("Nhân viên không tồn tại hoặc ID không hợp lệ");
            return false;
        }
        return employeeDAO.updateEmployee(emp);
    }

    // Cập nhật trạng thái nhân viên (ẩn/xóa mềm)
    public boolean updateEmployeeStatus(int id, boolean status) {
        // Kiểm tra điều kiện
        return employeeDAO.updateEmployeeStatus(id, status);
    }

    // Tìm kiếm nhân viên theo từ khóa
    public List<EmployeeDTO> searchEmployees(String keyword) {
        return employeeDAO.search(keyword);
    }

    public List<EmployeeDTO> getEmplNotAccount() {
        return employeeDAO.getEmplNotAccount();
    }

    public EmployeeDTO getById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public boolean isExistPhoneNumber(String phone) {
        return employeeDAO.isExistPhoneNumber(phone);
    }

    public List<EmployeeDTO> getEmployeesNotInAnyProject() {
        return employeeDAO.getEmployeesNotInAnyProject();
    }

    // Hàm thống kê số lượng Nam và Nữ
    public int[] getGenderStatistics() {
        int maleCount = 0;
        int femaleCount = 0;
        
        List<EmployeeDTO> employees = employeeDAO.getAllEmployees();

        for (EmployeeDTO employee : employees) {
            if ("Nam".equalsIgnoreCase(employee.getGender())) {
                maleCount++;
            } else if ("Nữ".equalsIgnoreCase(employee.getGender())) {
                femaleCount++;
            }
        }

        return new int[]{maleCount, femaleCount};
    }
}
