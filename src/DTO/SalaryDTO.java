/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */

public class SalaryDTO {
    private int salaryId;
    private int employeeId;
    private int salaryAmount;
    private int month;
    private int year;
    private boolean status;

    public SalaryDTO() {
    }

    public SalaryDTO(int salaryId, int employeeId, int salaryAmount, int month, int year, boolean status) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.salaryAmount = salaryAmount;
        this.month = month;
        this.year = year;
        this.status = status;
    }

    public SalaryDTO(SalaryDTO other) {
        this(other.salaryId, other.employeeId, other.salaryAmount, other.month, other.year, other.status);
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(int salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


