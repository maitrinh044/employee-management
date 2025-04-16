/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
public class RewardDTO {
    private int rewardId;
    private int employeeId;
    private String rewardDate;
    private int rewardValue;
    private String description;
    boolean status;
    
    public RewardDTO() {
    }

    public RewardDTO(int rewardId, int employeeId, String rewardDate, int rewardValue, String description, boolean status) {
        this.rewardId = rewardId;
        this.employeeId = employeeId;
        this.rewardDate = rewardDate;
        this.rewardValue = rewardValue;
        this.description = description;
        this.status = status;
    }

    public RewardDTO(RewardDTO other) {
        this(other.rewardId, other.employeeId, other.rewardDate, other.rewardValue, other.description, other.status);
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(String rewardDate) {
        this.rewardDate = rewardDate;
    }

    public int getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(int rewardValue) {
        this.rewardValue = rewardValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

