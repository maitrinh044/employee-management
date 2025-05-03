/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */

import DAO.RewardDAO;
import DTO.RewardDTO;

import java.util.List;

public class RewardBUS {
    private final RewardDAO rewardDAO;

    public RewardBUS() {
        rewardDAO = new RewardDAO();
    }

    // Lấy tất cả các thưởng của nhân viên
    public List<RewardDTO> getAllRewards() {
        return rewardDAO.getAll();
    }
    
    public RewardDTO getById(int id) {
        return rewardDAO.getById(id);
    }

    // Thêm thưởng cho nhân viên
    public boolean addReward(RewardDTO reward) {
        // Kiểm tra các điều kiện trước khi thêm thưởng
        if (reward.getRewardValue() <= 0) {
            System.out.println("Giá trị thưởng phải lớn hơn 0");
            return false;
        }
        return rewardDAO.add(reward);
    }
    
    public boolean updateReward(RewardDTO reward) {
        return rewardDAO.update(reward);
    }

    // Cập nhật trạng thái thưởng
    public boolean updateRewardStatus(int rewardId, boolean status) {
        return rewardDAO.updateStatus(rewardId, status);
    }

    // Lọc thưởng theo điều kiện
    public List<RewardDTO> filterRewards(int employeeId, boolean status) {
        return rewardDAO.filterByEmployeeIdAndStatus(employeeId, status);
    }

    // Lấy thưởng của nhân viên trong khoảng thời gian (tháng/năm)
    public List<RewardDTO> getRewardsByDateRange(int employeeId, String startDate, String endDate) {
        return rewardDAO.getRewardsByDateRange(employeeId, startDate, endDate);
    }
    
    // Lấy tổng số thưởng của nhân viên trong tháng
    public int getTotalRewardForEmployeeInMonth(int employeeId, int month, int year) {
        return rewardDAO.getTotalRewardForEmployeeInMonth(employeeId, month, year);
    }
    
    public List<RewardDTO> searchByNameAndAmount(String keyword) {
        return rewardDAO.searchByNameAndAmount(keyword);
    }
}
