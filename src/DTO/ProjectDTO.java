/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author duyen
 */
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String startDate;
    private String endDate;
    private int managerId;
    boolean status;

    public ProjectDTO() {
    }

    public ProjectDTO(int projectId, String projectName, String startDate, String endDate, int managerId, boolean status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.managerId = managerId;
        this.status = status;
    }

    public ProjectDTO(ProjectDTO other) {
        this(other.projectId, other.projectName, other.startDate, other.endDate, other.managerId, other.status);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(boolean stt){
        this.status = stt;
    }
}
    

