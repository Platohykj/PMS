package org.example.positionapi.service.impl;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employee.model.Employee;
import org.example.position.model.Position;
import org.example.positionapi.feign.EmployeeFeignClient;
import org.example.positionapi.feign.PositionFeignClient;
import org.example.positionapi.request.RemoveEmployeePositionRequest;
import org.example.positionapi.request.SaveRequest;
import org.example.positionapi.request.UpdateEmployeeRequest;
import org.example.positionapi.respose.EmployeesRespose;
import org.example.positionapi.service.PositionapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PositionapiServiceImpl implements PositionapiService {

    @Autowired
    PositionFeignClient positionFeignClient;

    @Autowired
    EmployeeFeignClient employeeFeignClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Position> getPositionList() {
        Object raw = positionFeignClient.getAllPositions().getContent();
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(raw), objectMapper.getTypeFactory().constructCollectionType(List.class, Position.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse position list", e);
        }
    }

    @Override
    public void savePosition(Position position) {
        positionFeignClient.addPosition(position);
    }

    @Override
    public void deletePosition(Long id) {
        try {
            positionFeignClient.deletePosition(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete position with id: " + id, e);
        }
    }

    @Override
    public List<EmployeesRespose> getEmployeesByPosition(String department) {
        Object raw = employeeFeignClient.getEmployeeByDepartment(department).getContent();
        List<EmployeesRespose> employeesResposes = new ArrayList<>();
        try {
            employeesResposes = objectMapper.readValue(objectMapper.writeValueAsString(raw), objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeesRespose.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse employees by position", e);
        }
        for (EmployeesRespose employeesRespose : employeesResposes) {
            Long positionId = employeesRespose.getPositionId();
            if (positionId != null) {
                Object r = positionFeignClient.getPositionById(positionId).getContent();
                System.out.println(r);
                System.out.println("class: " + r.getClass());
                try {
                    List<Map<String, Object>> result = objectMapper.convertValue(r, new TypeReference<List<Map<String, Object>>>() {});
                    String name = result.get(0).get("name").toString();
                    employeesRespose.setPosition(name);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse position for employee with id: " + employeesRespose.getJobId(), e);
                }
            }else {
                employeesRespose.setPosition("无");
            }
        }
        return employeesResposes;
    }

    @Override
    public void updateEmployeePosition(UpdateEmployeeRequest updateEmployeeRequest) throws IOException {
        String positionName = updateEmployeeRequest.getPosition();
        Object raw = positionFeignClient.getPositionByName(positionName).getContent();
        List<Map<String, Object>> result = objectMapper.convertValue(raw, new TypeReference<List<Map<String, Object>>>() {});
        Number idNumber = (Number) result.get(0).get("id");
        Long id = idNumber.longValue();
        String jobId = updateEmployeeRequest.getJobId();
        raw = employeeFeignClient.getEmployeeById(jobId).getContent();
        Employee employee = objectMapper.convertValue(raw, Employee.class);
        employee.setPositionId(id);
        employeeFeignClient.updateEmployee(employee);
    }

    @Override
    public void removeEmployeePosition(RemoveEmployeePositionRequest removeEmployeePositionRequest) {
        String jobId = removeEmployeePositionRequest.getJobId();
        Object raw = employeeFeignClient.getEmployeeById(jobId).getContent();
        Employee employee = objectMapper.convertValue(raw, Employee.class);
        employee.setPositionId(null);
        employeeFeignClient.updateEmployee(employee);
    }


}
