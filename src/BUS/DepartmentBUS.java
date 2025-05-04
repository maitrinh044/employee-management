/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */
import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBUS {

    private final DepartmentDAO departmentDAO;

    public DepartmentBUS() {
        departmentDAO = new DepartmentDAO();
    }

    public DepartmentDTO getDepartmentById(int departmentId) {
        List<DepartmentDTO> departments = getAllDepartments();
        for (DepartmentDTO department : departments) {
            if (department.getDepartmentId() == departmentId) {
                return department;
            }
        }
        return null; // Không tìm thấy
    }

    // Lấy danh sách tất cả các phòng ban đang hoạt động
    public List<DepartmentDTO> getAllDepartments() {
        return departmentDAO.getAll();
    }

    // Thêm phòng ban mới
    public boolean addDepartment(DepartmentDTO department) {
        if (department == null || department.getDepartmentName() == null || department.getDepartmentName().trim().isEmpty()) {
            System.out.println("Tên phòng ban không được để trống");
            return false;
        }
        return departmentDAO.add(department);
    }

    // Cập nhật thông tin phòng ban
    public boolean updateDepartment(DepartmentDTO department) {
        System.out.println(department.getDepartmentId() + " " + department.getDepartmentName());
        if (department == null || department.getDepartmentId() <= 0) {
            System.out.println("Thông tin phòng ban không hợp lệ");
            return false;
        }
        return departmentDAO.update(department);
    }

    // Cập nhật trạng thái (ẩn/hiện) của phòng ban
    public boolean updateDepartmentStatus(int id, boolean status) {
        if (id <= 0) {
            System.out.println("ID phòng ban không hợp lệ");
            return false;
        }
        return departmentDAO.updateStatus(id, status);
    }

    public boolean deleteDepartment(int departmentId) {
        // Kiểm tra trạng thái của phòng ban (status phải là false mới xóa được)
        DepartmentDTO department = departmentDAO.getById(departmentId);
        if (department != null && !department.isStatus()) {
            return departmentDAO.delete(departmentId); // Xóa phòng ban nếu trạng thái là false
        } else {
            return false;
        }
    }

    public List<DepartmentDTO> searchDepartments(String keyword) {
        List<DepartmentDTO> allDepartments = departmentDAO.getAll();
        List<DepartmentDTO> searchResults = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return allDepartments;
        }

        keyword = keyword.trim().toLowerCase();

        for (DepartmentDTO department : allDepartments) {
            if (department.getDepartmentName().toLowerCase().contains(keyword)) {
                searchResults.add(department);
            }
        }
        return searchResults;
    }

    public DepartmentDTO getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }
}
