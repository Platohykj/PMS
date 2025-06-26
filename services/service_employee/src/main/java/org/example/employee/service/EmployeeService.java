package org.example.employee.service;


import org.example.employee.model.Employee;
import org.example.user.model.User;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeByjobId(String jobId);

    void addEmployee(Employee employee);

    void deleteEmployee(String jobId);

    void updateEmployee(Employee employee);
}
