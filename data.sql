-- INSERT INTO roles
INSERT INTO roles (role_id, role_name, status) VALUES
(1, 'Admin', 1),
(2, 'Manager', 1),
(3, 'Employee', 1);

-- INSERT INTO positions
INSERT INTO positions (position_id, position_name, base_salary, status) VALUES
(1, 'Software Engineer', 1000, 1),
(2, 'Project Manager', 1500, 1),
(3, 'HR Specialist', 900, 1);

-- INSERT INTO departments (manager_id cập nhật sau)
INSERT INTO departments (department_id, department_name, manager_id, status) VALUES
(1, 'IT Department', NULL, 1),
(2, 'HR Department', NULL, 1);

-- INSERT INTO employees
INSERT INTO employees (employee_id, full_name, birthday, gender, phone_number, address, position_id, department_id, status) VALUES
(1, 'Nguyen Van A', '1990-01-01', 'Male', 123456789, '123 Le Loi', 1, 1, 1),
(2, 'Tran Thi B', '1992-03-15', 'Female', 987654321, '456 Tran Hung Dao', 2, 1, 1),
(3, 'Le Van C', '1985-07-20', 'Male', 112233445, '789 Nguyen Trai', 3, 2, 1);

-- Cập nhật manager_id cho departments
UPDATE departments SET manager_id = 2 WHERE department_id = 1;
UPDATE departments SET manager_id = 3 WHERE department_id = 2;

-- INSERT INTO accounts
INSERT INTO accounts (account_id, employee_id, username, role_id, created_at, status) VALUES
(1, 1, 'nguyenvana', 3, NOW(), 1),
(2, 2, 'tranthib', 2, NOW(), 1),
(3, 3, 'levanc', 2, NOW(), 1);

-- INSERT INTO salarys
INSERT INTO salarys (salary_id, employee_id, salary_amount, month, status) VALUES
(1, 1, 1000, '2025-03-01', 1),
(2, 2, 1500, '2025-03-01', 1),
(3, 3, 900, '2025-03-01', 1);

-- INSERT INTO projects
INSERT INTO projects (project_id, project_name, start_date, end_date, manager_id, status) VALUES
(1, 'Project Alpha', '2025-01-01', '2025-06-30', 2, 1),
(2, 'Project Beta', '2025-02-01', '2025-07-31', 2, 1);

-- INSERT INTO project_employee
INSERT INTO project_employee (project_id, employee_id, role_in_project, join_date, leave_date, status) VALUES
(1, 1, 'Developer', '2025-01-01', NULL, 1),
(1, 2, 'Manager', '2025-01-01', NULL, 1),
(2, 3, 'HR Support', '2025-02-01', NULL, 1);

-- INSERT INTO rewards
INSERT INTO rewards (reward_id, employee_id, reward_date, reward_value, description, status) VALUES
(1, 1, '2025-03-10', 200, 'Best Developer of the Month', 1),
(2, 2, '2025-03-15', 300, 'Project Leadership', 1);

-- INSERT INTO discipline
INSERT INTO discipline (discipline_id, employee_id, discipline_type, discipline_amount, description, status) VALUES
(1, 3, 'Warning', 0, 'Late to work frequently', 1),
(2, 1, 'Fine', 100, 'Missed deadline', 1);
