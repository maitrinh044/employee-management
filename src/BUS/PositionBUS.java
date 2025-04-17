/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */

import DAO.PositionDAO;
import DTO.PositionDTO;
import java.util.List;

public class PositionBUS {
    private final PositionDAO positionDAO;

    public PositionBUS() {
        positionDAO = new PositionDAO();
    }

    // Lấy danh sách chức vụ đang hoạt động
    public List<PositionDTO> getAllPositions() {
        return positionDAO.getAllPositions();
    }

    // Thêm chức vụ
    public boolean addPosition(PositionDTO position) {
        if (!isValidPosition(position, false)) return false;
        return positionDAO.addPosition(position);
    }

    // Cập nhật chức vụ
    public boolean updatePosition(PositionDTO position) {
        if (!isValidPosition(position, true)) return false;
        return positionDAO.updatePosition(position);
    }

    // Cập nhật trạng thái
    public boolean updatePositionStatus(int id, boolean status) {
        if (id <= 0) {
            System.out.println("ID chức vụ không hợp lệ");
            return false;
        }
        return positionDAO.updatePositionStatus(id, status);
    }

//     Tìm kiếm chức vụ
    public List<PositionDTO> searchPositions(String keyword) {
        return positionDAO.search(keyword);
    }

    private boolean isValidPosition(PositionDTO position, boolean isUpdate) {
        if (position == null) {
            System.out.println("Dữ liệu chức vụ không được null");
            return false;
        }

        if (isUpdate && position.getPositionId() <= 0) {
            System.out.println("ID chức vụ không hợp lệ");
            return false;
        }

        String name = position.getPositionName();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Tên chức vụ không được để trống");
            return false;
        }

        if (position.getBaseSalary() < 0) {
            System.out.println("Lương cơ bản phải >= 0");
            return false;
        }

        return true;
    }
    
    // Lấy thông tin vị trí của nhân viên theo employeeId
    public PositionDTO getPositionByEmployeeId(int employeeId) {
        return positionDAO.getPositionByEmployeeId(employeeId);
    }
}


