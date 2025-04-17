/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */

import DAO.AccountDAO;
import DTO.AccountDTO;

import java.util.List;

public class AccountBUS {
    private final AccountDAO accountDAO;

    public AccountBUS() {
        accountDAO = new AccountDAO();
    }

    // Lấy tất cả tài khoản
    public List<AccountDTO> getAllAccounts() {
        return accountDAO.getAll();
    }

    // Thêm tài khoản mới
    public boolean addAccount(AccountDTO account) {
        // Kiểm tra điều kiện (VD: Kiểm tra trùng username hoặc điều kiện khác)
        if (account.getUsername() == null || account.getUsername().isEmpty()) {
            System.out.println("Username không được để trống.");
            return false;
        }
        return accountDAO.add(account);
    }

    // Cập nhật tài khoản
    public boolean updateAccount(AccountDTO account) {
        // Kiểm tra điều kiện (VD: Kiểm tra nếu tài khoản có tồn tại hay không)
        if (account.getAccountId() <= 0) {
            System.out.println("Account ID không hợp lệ.");
            return false;
        }
        return accountDAO.update(account);
    }

    // Cập nhật trạng thái tài khoản (kích hoạt hoặc vô hiệu hóa)
    public boolean updateAccountStatus(int accountId, boolean status) {
        return accountDAO.updateStatus(accountId, status);
    }

    // Tìm kiếm tài khoản theo username
    public AccountDTO searchAccountByUsername(String username) {
        return accountDAO.getByUsername(username);
    }

    // Lọc tài khoản theo vai trò
    public List<AccountDTO> getAccountsByRole(int roleId) {
        return accountDAO.getByRole(roleId);
    }

    // Lấy tài khoản của nhân viên theo employeeId
    public AccountDTO getAccountByEmployeeId(int employeeId) {
        return accountDAO.getByEmployeeId(employeeId);
    }
}
