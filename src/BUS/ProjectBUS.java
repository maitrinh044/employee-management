/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author duyen
 */
import DAO.ProjectDAO;
import DTO.ProjectDTO;
import java.util.List;
import java.util.ArrayList;

public class ProjectBUS {

    private final ProjectDAO projectDAO;

    public ProjectBUS() {
        projectDAO = new ProjectDAO();
    }

    // Lấy tất cả dự án đang hoạt động
    public List<ProjectDTO> getAllProjects() {
        return projectDAO.getAll();
    }

    // Thêm dự án
    public boolean addProject(ProjectDTO project) {
        if (!isValidProject(project, false)) {
            return false;
        }
        return projectDAO.add(project);
    }

    // Cập nhật dự án
    public boolean updateProject(ProjectDTO project) {
        if (!isValidProject(project, true)) {
            return false;
        }
        return projectDAO.update(project);
    }

    // Xóa dự án (Ẩn dự án bằng Cập nhật trạng thái)
    public boolean updateProjectStatus(int id) {
        if (id <= 0) {
            System.out.println("ID dự án không hợp lệ");
            return false;
        }
        return projectDAO.updateStatus(id, false);
    }

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
        if (project.getStartDate() == null || project.getStartDate() == null) {
            System.out.println("Ngày bắt đầu không được để trống");
            return false;
        }
        if (project.getEndDate() == null || project.getEndDate() == null) {
            System.out.println("Ngày kết thúc không được để trống");
            return false;
        }

        if (project.getManagerId() <= 0) {
            System.out.println("ID quản lý không hợp lệ");
            return false;
        }

        return true;
    }

    public List<ProjectDTO> searchProjects(String keyword) {
        List<ProjectDTO> allProjects = projectDAO.getAll();
        List<ProjectDTO> searchResults = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return allProjects;
        }

        keyword = keyword.trim().toLowerCase();

        for (ProjectDTO project : allProjects) {
            if (project.getProjectName().toLowerCase().contains(keyword)) {
                searchResults.add(project);
            }
        }
        return searchResults;
    }

    public ProjectDTO getProjectById(int projectId) {
        if (projectId <= 0) {
            System.out.println("ID dự án không hợp lệ");
            return null; // Hoặc throw một exception nếu bạn thích
        }
        return projectDAO.getProjectById(projectId);
    }

    public int[] getActiveStatistics() {
        int active = 0;
        int inactive = 0;

        List<ProjectDTO> pr = projectDAO.getAll();

        for (ProjectDTO p : pr) {
            if (p.getStatus()) {
                active++;
            } else {
                inactive++;
            }
        }

        return new int[]{active, inactive};
    }
}
