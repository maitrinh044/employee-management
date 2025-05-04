/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */
import DAO.DisciplineDAO;
import DTO.DisciplineDTO;

import java.util.List;

public class DisciplineBUS {
    private final DisciplineDAO disciplineDAO;

    public DisciplineBUS() {
        disciplineDAO = new DisciplineDAO();
    }

    // Lấy tất cả các kỷ luật của nhân viên
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineDAO.getAll();
    }
    
    public DisciplineDTO getById(int id) {
        return disciplineDAO.getById(id);
    }

    // Thêm kỷ luật cho nhân viên
    public boolean addDiscipline(DisciplineDTO discipline) {
        // Kiểm tra các điều kiện trước khi thêm kỷ luật
        if (discipline.getDisciplineAmount() <= 0) {
            System.out.println("Số tiền phạt phải lớn hơn 0");
            return false;
        }
        return disciplineDAO.add(discipline);
    }
    
    public boolean update(DisciplineDTO disc) {
        return disciplineDAO.update(disc);
    }

    public List<DisciplineDTO> searchDisByNameAndAmount(String keyword) {
        return disciplineDAO.searchDisByNameAndAmount(keyword);
    }

    // Cập nhật trạng thái kỷ luật
    public boolean updateDisciplineStatus(int disciplineId, boolean status) {
        return disciplineDAO.updateStatus(disciplineId, status);
    }

    // Lọc kỷ luật theo điều kiện
    public List<DisciplineDTO> filterDisciplines(int employeeId, boolean status) {
        return disciplineDAO.filterByEmployeeIdAndStatus(employeeId, status);
    }

    // Lấy kỷ luật của nhân viên trong khoảng thời gian (tháng/năm)
    public List<DisciplineDTO> getDisciplinesByDateRange(int employeeId, String startDate, String endDate) {
        return disciplineDAO.getDisciplinesByDateRange(employeeId, startDate, endDate);
    }
    
    // Lấy tổng số kỷ luật của nhân viên trong tháng
    public int getTotalDisciplineForEmployeeInMonth(int employeeId, int month, int year) {
        return disciplineDAO.getTotalDisciplineForEmployeeInMonth(employeeId, month, year);
    }
}
