package org.example.department.service;


import org.example.department.model.Department;

import java.util.List;


public interface DepartmentService {

    void addDepartment(Department department);



    Department getDepartmentByName(String name);

    void deleteDepartment(Department department);

    List<Department> getAllDepartments();

    void deleteParentDepartment(String parentDepartmentName);

    List<Department> getAllParentDepartments();

    List<Department> getSubDepartmentsByParentName(String parentDepartmentName);
}


