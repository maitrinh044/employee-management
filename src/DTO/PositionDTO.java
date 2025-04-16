/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
public class PositionDTO {
    private int positionId;
    private String positionName;
    private int baseSalary;
    private boolean status;

    public PositionDTO() {
    }

    public PositionDTO(int positionId, String positionName, int baseSalary, boolean status) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.baseSalary = baseSalary;
        this.status = status;
    }

    public PositionDTO(PositionDTO pos) {
        this.positionId = pos.positionId;
        this.positionName = pos.positionName;
        this.baseSalary = pos.baseSalary;
        this.status = pos.status;
    }
    
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PositionDTO{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", baseSalary=" + baseSalary +
                ", status=" + status +
                '}';
    }
}

