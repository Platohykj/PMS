package org.example.departmentapi.service;


import org.example.department.model.Department;
import org.example.departmentapi.request.*;
import org.example.user.model.User;

import java.util.List;
import java.util.Map;

public interface DepartmentapiService {

    Map<String, List<String>> getDepartmentMap();

    void addParentDepartment(AddParentRequest addParentRequest);

    void addSubDepartment(AddSubRequest addSubRequest);

    void renameParentDepartment(RenameParentRequest renameParentRequest);

    void deleteSubDepartment(DeletSubRequest deletSubRequest);

    void deleteParentDepartment(DeletParentRequest deletParentRequest);

    List<String> getAllDepartments();

    List<String> getSubDepartmentsByParentName(SubListRequest subListRequest);
}
