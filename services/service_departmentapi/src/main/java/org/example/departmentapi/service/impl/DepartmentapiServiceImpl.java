package org.example.departmentapi.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.department.model.Department;
import org.example.departmentapi.feign.DepartmentFeignClient;
import org.example.departmentapi.feign.EmployeeFeignClient;
import org.example.departmentapi.request.*;
import org.example.departmentapi.service.DepartmentapiService;

import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentapiServiceImpl implements DepartmentapiService {

    @Autowired
    DepartmentFeignClient departmentFeignClient;

    @Autowired
    EmployeeFeignClient employeeFeignClient;


    @Autowired
    private ObjectMapper objectMapper; // 确保已注入 Jackson 的 ObjectMapper

    public Map<String, List<String>> getDepartmentMap() {
        List<?> rawList = (List<?>) departmentFeignClient.getAllDepartments().getContent();
        if (rawList == null || rawList.isEmpty()) {
            throw new RuntimeException("No departments found");

        }
        System.out.println("----------------------------");

        // 转换为 Department 对象列表
        List<Department> departments = rawList.stream()
                .map(item -> objectMapper.convertValue(item, Department.class))
                .collect(Collectors.toList());

        // 构建 parentId -> List<Department> 的映射
        Map<Long, List<Department>> parentMap = departments.stream()
                .filter(dept -> dept.getParent_id() != 0)
                .collect(Collectors.groupingBy(Department::getParent_id));

        // 构建一级部门 -> 子部门列表
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (Department dept : departments) {
            if (dept.getParent_id() == 0) {
                List<Department> children = parentMap.get(dept.getId());
                if (children != null && !children.isEmpty()) {
                    List<String> childNames = children.stream()
                            .map(Department::getName)
                            .collect(Collectors.toList());
                    result.put(dept.getName(), childNames);
                }else{
                    // 如果没有子部门，仍然添加一级部门
                    result.put(dept.getName(), List.of());
                }
            }
        }

        return result;
    }

    @Override
    public void addParentDepartment(AddParentRequest addParentRequest) {
        Department department = new Department();
        department.setName(addParentRequest.getParent());
        department.setParent_id(0L); // 设置为一级部门
        departmentFeignClient.addDepartment(department);
    }

    @Override
    public void addSubDepartment(AddSubRequest addSubRequest) {
        Department department = new Department();
        department.setName(addSubRequest.getChild());

        Object raw = departmentFeignClient.getDepartmentByName(addSubRequest.getParent()).getContent();
        Department parentDepartment = objectMapper.convertValue(raw, Department.class);

        if (parentDepartment == null) {
            throw new RuntimeException("Parent department not found");
        }

        department.setParent_id(parentDepartment.getId());
        departmentFeignClient.addDepartment(department);
    }

    @Override
    public void renameParentDepartment(RenameParentRequest renameParentRequest) {
        Object oldRaw = departmentFeignClient.getDepartmentByName(renameParentRequest.getOldName()).getContent();
        Department oldDepartment = objectMapper.convertValue(oldRaw, Department.class);
        if (oldDepartment == null) {
            throw new RuntimeException("Old department not found");
        }
        Department newDepartment = new Department();
        newDepartment.setId(oldDepartment.getId());
        newDepartment.setName(renameParentRequest.getNewName());
        newDepartment.setParent_id(0L); // 确保是一级部门
        departmentFeignClient.updateDepartment(newDepartment);

    }

    @Override
    public void deleteSubDepartment(DeletSubRequest deletSubRequest) {
        Object raw = departmentFeignClient.getDepartmentByName(deletSubRequest.getChild()).getContent();
        Department department = objectMapper.convertValue(raw, Department.class);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        departmentFeignClient.deleteDepartment(department);
        employeeFeignClient.clearSubDepartment(department.getName());
    }

    @Override
    public void deleteParentDepartment(DeletParentRequest deletParentRequest) {
        Object raw = departmentFeignClient.deleteParentDepartment(deletParentRequest.getParent()).getContent();
        employeeFeignClient.clearSubDepartment(deletParentRequest.getParent());
    }

    @Override
    public List<String> getAllDepartments() {
        Object raw = departmentFeignClient.getAllParentDepartments().getContent();
        if (raw == null) {
            throw new RuntimeException("No parent departments found");
        }
        List<Department> departments = ((List<?>) raw).stream()
                .map(item -> objectMapper.convertValue(item, Department.class))
                .collect(Collectors.toList());
        List<String> departmentNames = departments.stream()
                .map(Department::getName)
                .collect(Collectors.toList());
        if (departmentNames.isEmpty()) {
            throw new RuntimeException("No parent departments found");
        }
        return departmentNames;
    }

    @Override
    public List<String> getSubDepartmentsByParentName(SubListRequest subListRequest) {
        String parentDepartmentName = subListRequest.getParent();
        Object raw = departmentFeignClient.getSubDepartmentsByParentName(parentDepartmentName).getContent();
        if (raw == null) {
            throw new RuntimeException("No sub-departments found for parent: " + parentDepartmentName);
        }
        List<Department> subDepartments = ((List<?>) raw).stream()
                .map(item -> objectMapper.convertValue(item, Department.class))
                .collect(Collectors.toList());
        if (subDepartments.isEmpty()) {
            throw new RuntimeException("No sub-departments found for parent: " + parentDepartmentName);
        }
        return subDepartments.stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }


}
