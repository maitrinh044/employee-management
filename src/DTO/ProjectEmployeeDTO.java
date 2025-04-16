/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MaiTrinh
 */
public class ProjectEmployeeDTO {
    private int projectId;
    private int employeeId;
    private String roleInProject;
    private String joinDate;
    private String leaveDate;
    boolean status;

    public ProjectEmployeeDTO() {
    }

    public ProjectEmployeeDTO(int projectId, int employeeId, String roleInProject, String joinDate, String leaveDate, boolean status) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.roleInProject = roleInProject;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.status = status;
    }

    public ProjectEmployeeDTO(ProjectEmployeeDTO other) {
        this(other.projectId, other.employeeId, other.roleInProject, other.joinDate, other.leaveDate, other.status);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRoleInProject() {
        return roleInProject;
    }

    public void setRoleInProject(String roleInProject) {
        this.roleInProject = roleInProject;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

