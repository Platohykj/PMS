package org.example.department.service;


import org.example.department.model.Department;


public interface DepartmentService {

    void addDepartment(Department department);



    Department getDepartmentByName(String name);

    void deleteDepartment(Department department);

}


