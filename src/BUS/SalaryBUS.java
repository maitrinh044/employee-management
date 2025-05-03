package BUS;

/**
 *
 * @author MaiTrinh
 */

import BUS.DisciplineBUS;
import BUS.PositionBUS;
import BUS.RewardBUS;
import DAO.SalaryDAO;
import DTO.PositionDTO;
import DTO.SalaryDTO;
import java.util.List;

public class SalaryBUS {
    private final SalaryDAO salaryDAO;
    private final PositionBUS positionBUS;
    private final RewardBUS rewardBUS;
    private final DisciplineBUS disciplineBUS;

    public SalaryBUS() {
        salaryDAO = new SalaryDAO();
        this.positionBUS = new PositionBUS();
        this.rewardBUS = new RewardBUS();
        this.disciplineBUS = new DisciplineBUS();
    }

    // Lấy danh sách lương đang hoạt động
    public List<SalaryDTO> getAllSalaries() {
        return salaryDAO.getAll();
    }
    
    public SalaryDTO getById(int id) {
        return salaryDAO.getById(id);
    }

    // Thêm bản ghi lương mới
    public boolean addSalary(SalaryDTO salary) {
        // Kiểm tra điều kiện đầu vào nếu cần
        if (salary.getSalaryAmount() <= 0 || salary.getMonth() <= 0 || salary.getMonth() > 12 || salary.getYear() <= 0) {
            System.err.println("Dữ liệu lương không hợp lệ!");
            return false;
        }
        return salaryDAO.add(salary);
    }

    // Cập nhật thông tin lương
    public boolean updateSalary(SalaryDTO salary) {
        // Kiểm tra điều kiện nếu cần
        return salaryDAO.update(salary);
    }

    // Cập nhật trạng thái (ẩn/hiện)
    public boolean updateSalaryStatus(int id, boolean status) {
        return salaryDAO.updateStatus(id, status);
    }
    
    // Lấy danh sách lương theo employeeId
    public List<SalaryDTO> getSalariesByEmployeeId(int employeeId) {
        return salaryDAO.getByEmployeeId(employeeId);
    }

    // Lọc lương theo employeeId, month và year
    public List<SalaryDTO> getSalariesByEmployeeAndMonthYear(int employeeId, int month, int year) {
        return salaryDAO.getByEmployeeAndMonthYear(employeeId, month, year);
    }
    
    public List<SalaryDTO> getByMonthYear(int month, int year) {
        return salaryDAO.getByMonthYear(month, year);
    }
    
    public List<Integer> getAllYear() {
        return salaryDAO.getAllYear();
    }
    
    public List<SalaryDTO> searchByNameAndAmount(String keyword) {
        return salaryDAO.searchByNameAndAmount(keyword);
    }
    
    // Tính lương của nhân viên theo employeeId trong tháng và năm
    public int calculateSalary(int employeeId, int month, int year) {
        // Lấy thông tin vị trí của nhân viên từ PositionBUS
        PositionDTO position = positionBUS.getPositionByEmployeeId(employeeId);
        int baseSalary = position != null ? position.getBaseSalary() : 0;

        // Lấy tổng phần thưởng của nhân viên trong tháng từ RewardBUS
        int totalReward = rewardBUS.getTotalRewardForEmployeeInMonth(employeeId, month, year);

        // Lấy tổng kỷ luật của nhân viên trong tháng từ DisciplineBUS
        int totalDiscipline = disciplineBUS.getTotalDisciplineForEmployeeInMonth(employeeId, month, year);

        // Tính tổng lương
        int totalSalary = baseSalary + totalReward - totalDiscipline;

        return totalSalary;
    }
}

