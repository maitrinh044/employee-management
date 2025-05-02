/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RoleDAO;

/**
 *
 * @author MaiTrinh
 */
public class RoleBUS {
    private final RoleDAO roleDAO;
    
    public RoleBUS() {
        roleDAO = new RoleDAO();
    }

    public String getRoleNameById(int roleId) {
        return roleDAO.getRoleNameById(roleId);
    }

}
