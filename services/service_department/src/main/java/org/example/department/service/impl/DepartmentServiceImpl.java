package org.example.department.service.impl;


import org.example.department.model.Department;
import org.example.department.repository.DepartmentRepository;
import org.example.department.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteParentDepartment(String parentDepartmentName) {
        if (parentDepartmentName == null || parentDepartmentName.isEmpty()) {
            throw new IllegalArgumentException("Parent department name cannot be null or empty");
        }
        Department parentDepartment = departmentRepository.findByName(parentDepartmentName);
        if (parentDepartment != null) {
            departmentRepository.delete(parentDepartment);
        } else {
            throw new IllegalArgumentException("Parent department not found: " + parentDepartmentName);
        }
        Long parentId = parentDepartment.getId();
        List<Department> childDepartments = departmentRepository.findByParentId(parentId);
        departmentRepository.deleteAll(childDepartments);
    }

    @Override
    public List<Department> getAllParentDepartments() {
        List<Department> allDepartments = departmentRepository.findByParentId(0L);
        if (allDepartments == null || allDepartments.isEmpty()) {
            throw new IllegalArgumentException("No parent departments found");
        }
        return allDepartments;
    }

    @Override
    public List<Department> getSubDepartmentsByParentName(String parentDepartmentName) {
        if (parentDepartmentName == null || parentDepartmentName.isEmpty()) {
            throw new IllegalArgumentException("Parent department name cannot be null or empty");
        }
        Department parentDepartment = departmentRepository.findByName(parentDepartmentName);
        if (parentDepartment == null) {
            throw new IllegalArgumentException("Parent department not found: " + parentDepartmentName);
        }
        Long parentId = parentDepartment.getId();
        List<Department> subDepartments = departmentRepository.findByParentId(parentId);
        if (subDepartments == null || subDepartments.isEmpty()) {
            throw new IllegalArgumentException("No sub-departments found for parent department: " + parentDepartmentName);
        }
        return subDepartments;
    }
}
