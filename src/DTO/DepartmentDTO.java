/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
public class DepartmentDTO {
    private int departmentId;
    private String departmentName;
    private int managerId;
    private boolean status;

    // Constructors
    public DepartmentDTO() {
    }

    public DepartmentDTO(int departmentId, String departmentName, int managerId, boolean status) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.status = status;
    }
    
    public DepartmentDTO(DepartmentDTO dpm) {
        this.departmentId = dpm.departmentId;
        this.departmentName = dpm.departmentName;
        this.managerId = dpm.managerId;
        this.status = dpm.status;
    }

    // Getters and Setters
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", managerId=" + managerId +
                ", status=" + status +
                '}';
    }
}
