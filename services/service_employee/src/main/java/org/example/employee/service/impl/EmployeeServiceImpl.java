package org.example.employee.service.impl;


import org.example.employee.model.Employee;
import org.example.employee.repository.EmployeeRepository;
import org.example.employee.service.EmployeeService;
import org.example.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found");
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByjobId(String jobId) {
        Employee employee = employeeRepository.findByJobId(jobId);
        if (employee == null) {
            throw new RuntimeException("Employee with jobId: " + jobId + " not found");
        }
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employee == null || employee.getJobId() == null || employee.getName() == null) {
            throw new IllegalArgumentException("Employee details are incomplete");
        }
        Employee existingEmployee = employeeRepository.findByJobId(employee.getJobId());
        if (existingEmployee != null) {
            throw new RuntimeException("Employee with jobId: " + employee.getJobId() + " already exists");
        }
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String jobId) {
        Employee employee = employeeRepository.findByJobId(jobId);
        if (employee == null) {
            throw new RuntimeException("Employee with jobId: " + jobId + " not found");
        }
        employeeRepository.delete(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void clearSubDepartment(String subdepartment) {
        // 查找所有subdepartment为指定值的员工
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found in subdepartment: " + subdepartment);
        }
        for (Employee employee : employees) {
            if (subdepartment.equals(employee.getSubDepartment())) {
                // 清除subdepartment字段
                employee.setSubDepartment(null);
                employeeRepository.save(employee);
            }
        }
    }

    @Override
    public void clearParentDepartments(String parentDepartment) {
        // 查找所有parentDepartment为指定值的员工
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found in parent department: " + parentDepartment);
        }
        for (Employee employee : employees) {
            if (parentDepartment.equals(employee.getDepartment())) {
                // 清除parentDepartment字段
                employee.setDepartment(null);
                employee.setSubDepartment(null);
                employeeRepository.save(employee);
            }
        }
    }

    @Override
    public List<Employee> getEmployeeByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found in department: " + department);
        }
        return employees;
    }

    @Override
    public void updateTitleId(String jobId, Long titleId) {
        Employee employee = employeeRepository.findByJobId(jobId);
        if (employee == null) {
            throw new RuntimeException("Employee with jobId: " + jobId + " not found");
        }
        employee.setTitle_id(titleId);
        employeeRepository.save(employee);
    }

    @Override
    public void removeTitle(String jobId) {
        Employee employee = employeeRepository.findByJobId(jobId);
        if (employee == null) {
            throw new RuntimeException("Employee with jobId: " + jobId + " not found");
        }
        employee.setTitle_id(null);
        employeeRepository.save(employee);
    }

    @Override
    public void updateDepartment(String jobId, String department) {
        Employee employee = employeeRepository.findByJobId(jobId);
        if (employee == null) {
            throw new RuntimeException("Employee with jobId: " + jobId + " not found");
        }
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    @Override
    public List<String> getjobidByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found in department: " + department);
        }
        return employees.stream()
                .map(Employee::getJobId)
                .toList();
    }
}
