DROP DATABASE IF EXISTS employee_management;

CREATE DATABASE employee_management;

USE employee_management;

-- Drop tables if they exist (order matters)
DROP TABLE IF EXISTS discipline;
DROP TABLE IF EXISTS rewards;
DROP TABLE IF EXISTS project_employee;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS salarys;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS roles;

-- Create roles table
CREATE TABLE roles (
  role_id INT AUTO_INCREMENT PRIMARY KEY,
  role_name VARCHAR(255),
  status INT DEFAULT 1
);

-- Create positions table
CREATE TABLE positions (
  position_id INT AUTO_INCREMENT PRIMARY KEY,
  position_name VARCHAR(255),
  base_salary INT,
  status INT DEFAULT 1
);

-- Create departments table
CREATE TABLE departments (
  department_id INT AUTO_INCREMENT PRIMARY KEY,
  department_name VARCHAR(255),
  manager_id INT UNIQUE,
  status INT DEFAULT 1,
  FOREIGN KEY (manager_id) REFERENCES employees(employee_id)
);

-- Create employees table
CREATE TABLE employees (
  employee_id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255),
  birthday DATE,
  gender ENUM('Male', 'Female', 'Other'),
  phone_number BIGINT,
  address VARCHAR(255),
  position_id INT,
  department_id INT,
  status INT DEFAULT 1,
  FOREIGN KEY (position_id) REFERENCES positions(position_id),
  FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Create accounts table
CREATE TABLE accounts (
  account_id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT UNIQUE,
  username VARCHAR(255),
  password VARCHAR(255),
  role_id INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status INT DEFAULT 1,
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
  FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- Create salarys table
CREATE TABLE salarys (
  salary_id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT,
  salary_amount INT,
  month DATE,
  status INT DEFAULT 1,
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- Create projects table
CREATE TABLE projects (
  project_id INT AUTO_INCREMENT PRIMARY KEY,
  project_name VARCHAR(255),
  start_date DATE,
  end_date DATE,
  manager_id INT,
  status INT DEFAULT 1,
  FOREIGN KEY (manager_id) REFERENCES employees(employee_id)
);

-- Create project_employee table
CREATE TABLE project_employee (
  project_id INT,
  employee_id INT,
  role_in_project VARCHAR(255),
  join_date DATE,
  leave_date DATE,
  status INT DEFAULT 1,
  PRIMARY KEY (project_id, employee_id),
  FOREIGN KEY (project_id) REFERENCES projects(project_id),
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- Create rewards table
CREATE TABLE rewards (
  reward_id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT,
  reward_date DATE,
  reward_value INT,
  description VARCHAR(255),
  status INT DEFAULT 1,
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- Create discipline table
CREATE TABLE discipline (
  discipline_id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT,
  discipline_type VARCHAR(255),
  discipline_amount INT,
  description VARCHAR(255),
  status INT DEFAULT 1,
  FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
