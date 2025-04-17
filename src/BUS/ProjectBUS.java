/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */

import DAO.ProjectDAO;
import DTO.ProjectDTO;
import java.util.List;

public class ProjectBUS {
    private final ProjectDAO projectDAO;

    public ProjectBUS() {
        projectDAO = new ProjectDAO();
    }

    // Lấy tất cả dự án
    public List<ProjectDTO> getAllProjects() {
        return projectDAO.getAll();
    }

    // Thêm dự án
    public boolean addProject(ProjectDTO project) {
        if (!isValidProject(project, false)) return false;
        return projectDAO.add(project);
    }

    // Cập nhật dự án
    public boolean updateProject(ProjectDTO project) {
        if (!isValidProject(project, true)) return false;
        return projectDAO.update(project);
    }

    // Cập nhật trạng thái (ẩn/xóa mềm)
    public boolean updateProjectStatus(int id, boolean status) {
        if (id <= 0) {
            System.out.println("ID dự án không hợp lệ");
            return false;
        }
        return projectDAO.updateStatus(id, status);
    }

//    // Tìm kiếm theo từ khóa
//    public List<ProjectDTO> searchProjects(String keyword) {
//        return projectDAO.search(keyword);
//    }

    private boolean isValidProject(ProjectDTO project, boolean isUpdate) {
        if (project == null) {
            System.out.println("Dữ liệu dự án không được null");
            return false;
        }

        if (isUpdate && project.getProjectId() <= 0) {
            System.out.println("ID dự án không hợp lệ");
            return false;
        }

        if (project.getProjectName() == null || project.getProjectName().trim().isEmpty()) {
            System.out.println("Tên dự án không được để trống");
            return false;
        }

        if (project.getStartDate() == null || project.getEndDate() == null ||
            project.getStartDate().trim().isEmpty() || project.getEndDate().trim().isEmpty()) {
            System.out.println("Ngày bắt đầu/kết thúc không được để trống");
            return false;
        }

        if (project.getManagerId() <= 0) {
            System.out.println("ID quản lý không hợp lệ");
            return false;
        }

        return true;
    }
}

