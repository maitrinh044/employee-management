package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Date;

/**
 *
 * @author MaiTrinh
 */
public class EmployeeDTO {
    private int employeeId;
    private String fullName;
    private Date birthday;
    private String gender;
    private String phoneNumber;
    private String address;
    private int positionId;
    private int departmentId;
    private boolean status;

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeId, String fullName, Date birthday, String gender,
                       String phoneNumber, String address, int positionId, int departmentId, boolean status) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.status = status;
    }
    
    public EmployeeDTO(EmployeeDTO em){
        this.employeeId = em.employeeId;
        this.fullName = em.fullName;
        this.birthday = em.birthday;
        this.gender = em.gender;
        this.phoneNumber = em.phoneNumber;
        this.address = em.address;
        this.positionId = em.positionId;
        this.departmentId = em.departmentId;
        this.status = em.status;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", fullName='" + fullName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", positionId=" + positionId +
                ", departmentId=" + departmentId +
                ", status=" + status +
                '}';
    }
}
