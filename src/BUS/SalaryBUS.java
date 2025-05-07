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
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;

public class SalaryBUS {

    private final SalaryDAO salaryDAO;
    private final PositionBUS positionBUS;
    private final RewardBUS rewardBUS;
    private final DisciplineBUS disciplineBUS;
    private final EmployeeBUS emBUS;

    public SalaryBUS() {
        salaryDAO = new SalaryDAO();
        this.positionBUS = new PositionBUS();
        this.rewardBUS = new RewardBUS();
        this.disciplineBUS = new DisciplineBUS();
        emBUS = new EmployeeBUS();
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

    public List<SalaryDTO> getTop5HighestSalariesLastMonth() {
        return salaryDAO.getTop5HighestSalariesLastMonth();
    }

    public List<SalaryDTO> readExcelFile(String filePath) {
        List<SalaryDTO> salaryList = null;

        try {
            // Đọc file Excel và chuyển thành danh sách SalaryDTO
            salaryList = ExcelReader.readExcelFileGeneric(filePath, new ExcelReader.ExcelRowMapper<SalaryDTO>() {
                @Override
                public SalaryDTO map(Row row) {
                    SalaryDTO salary = new SalaryDTO();

                    // Ánh xạ dữ liệu từ các cột trong file Excel vào SalaryDTO
                    salary.setEmployeeId((int) row.getCell(0).getNumericCellValue()); // Cột 0: Employee ID
                    salary.setSalaryAmount((int) row.getCell(1).getNumericCellValue()); // Cột 1: Salary Amount
                    salary.setMonth((int) row.getCell(2).getNumericCellValue()); // Cột 2: Month
                    salary.setYear((int) row.getCell(3).getNumericCellValue()); // Cột 3: Year

                    return salary;
                }
            });

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(null, "Đã đọc thành công file Excel!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Nếu có lỗi xảy ra, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đọc file: " + e.getMessage(), "Lỗi đọc file", JOptionPane.ERROR_MESSAGE);
        }

        return salaryList;
    }

    public void writeSalaryToExcel(String filePath, List<SalaryDTO> salaryList) {
        // Kiểm tra nếu file đã tồn tại, nếu có thì xóa
        File file = new File(filePath);
        if (file.exists()) {
            file.delete(); // Xóa file nếu đã tồn tại
        }

        try {
            // Tạo các headers cho Excel
            String[] headers = {"Mã nhân viên", "Tên nhân viên", "Lương", "Tháng", "Năm"};

            // Gọi hàm của ExcelWriter để xuất dữ liệu
            ExcelWriter.writeExcelFileGeneric(filePath, salaryList, headers, salary -> new String[]{
                String.valueOf(salary.getEmployeeId()),
                emBUS.getById(salary.getEmployeeId()).getFullName(),
                String.valueOf(salary.getSalaryAmount()),
                String.valueOf(salary.getMonth()),
                String.valueOf(salary.getYear())
            });

            // Thông báo khi xuất thành công
            JOptionPane.showMessageDialog(null, "File Excel đã được xuất thành công: " + filePath, "Xuất file thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Thông báo khi có lỗi
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất file: " + e.getMessage(), "Lỗi xuất file", JOptionPane.ERROR_MESSAGE);
        }
    }

}
