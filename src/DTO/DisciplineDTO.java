/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
public class DisciplineDTO {
    private int disciplineId;
    private int employeeId;
    private String disciplineType;
    private int disciplineAmount;
    private String description;
    private boolean status;
    
    public DisciplineDTO() {
    }

    public DisciplineDTO(int disciplineId, int employeeId, String disciplineType, int disciplineAmount, String description, boolean status) {
        this.disciplineId = disciplineId;
        this.employeeId = employeeId;
        this.disciplineType = disciplineType;
        this.disciplineAmount = disciplineAmount;
        this.description = description;
        this.status = status;
    }
    
    public DisciplineDTO(DisciplineDTO disc) {
        this.disciplineId = disc.disciplineId;
        this.employeeId = disc.employeeId;
        this.disciplineType = disc.disciplineType;
        this.disciplineAmount = disc.disciplineAmount;
        this.description = disc.description;
        this.status = disc.status;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(String disciplineType) {
        this.disciplineType = disciplineType;
    }

    public int getDisciplineAmount() {
        return disciplineAmount;
    }

    public void setDisciplineAmount(int disciplineAmount) {
        this.disciplineAmount = disciplineAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
}

