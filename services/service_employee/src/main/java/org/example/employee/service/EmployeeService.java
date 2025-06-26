package org.example.employee.service;


import org.example.department.model.Department;
import org.example.employee.model.Employee;
import org.example.user.model.User;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeByjobId(String jobId);

    void addEmployee(Employee employee);

    void deleteEmployee(String jobId);

    void updateEmployee(Employee employee);

    void clearSubDepartment(String subdepartment);

    void clearParentDepartments(String parentDepartment);

    List<Employee> getEmployeeByDepartment(String department);

    void updateTitleId(String jobId, Long titleId);

    void removeTitle(String jobId);
}
