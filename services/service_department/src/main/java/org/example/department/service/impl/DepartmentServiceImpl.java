package org.example.department.service.impl;


import org.example.department.model.Department;
import org.example.department.repository.DepartmentRepository;
import org.example.department.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void addDepartment(Department department) {
        if (department == null || department.getName() == null || department.getParent_id() == null) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }
        return departmentRepository.findByName(name);
    }

    @Override
    public void deleteDepartment(Department department) {
        if (department == null || department.getId() == null) {
            throw new IllegalArgumentException("Department cannot be null or have a null ID");
        }
        departmentRepository.delete(department);
    }


}
