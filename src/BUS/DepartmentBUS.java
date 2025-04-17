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
import java.util.List;

public class DepartmentBUS {
    private final DepartmentDAO departmentDAO;

    public DepartmentBUS() {
        departmentDAO = new DepartmentDAO();
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

    // Tìm kiếm phòng ban theo tên (tuỳ chọn nếu có DAO search)
//    public List<DepartmentDTO> searchDepartments(String keyword) {
//        return departmentDAO.search(keyword);
//    }
}

