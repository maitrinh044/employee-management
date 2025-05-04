/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author MaiTrinh
 */
public class DisciplineDTO {
    private int disciplineId;
    private int employeeId;
    private int disciplineAmount;
    private String description;
    private Date discDate;
    private boolean status;
    
    public DisciplineDTO() {
    }

    public DisciplineDTO(int disciplineId, int employeeId, int disciplineAmount, String description, Date date, boolean status) {
        this.disciplineId = disciplineId;
        this.employeeId = employeeId;
        this.disciplineAmount = disciplineAmount;
        this.description = description;
        this.discDate = date;
        this.status = status;
    }
    
    public DisciplineDTO(DisciplineDTO disc) {
        this.disciplineId = disc.disciplineId;
        this.employeeId = disc.employeeId;
        this.disciplineAmount = disc.disciplineAmount;
        this.description = disc.description;
        this.discDate = disc.discDate;
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
    
    public Date getDate() {
        return discDate;
    }
    
    public void setDate(Date date) {
        this.discDate = date;
    } 
    
    public boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
}

