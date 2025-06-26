package org.example.hrapi.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.employee.model.Employee;
import org.example.hrapi.feign.EmployeeFeignClient;
import org.example.hrapi.feign.SalaryFeignClient;
import org.example.hrapi.feign.TitleFeignClient;
import org.example.hrapi.request.BatchSalary;
import org.example.hrapi.request.ListRequest;
import org.example.hrapi.request.UpadteRequest;
import org.example.hrapi.response.ListResponse;
import org.example.hrapi.service.HrapiService;
import org.example.response.Response;
import org.example.salary.model.Salary;
import org.example.title.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HrapiServiceImpl implements HrapiService {


    @Autowired
    TitleFeignClient titleFeignClient;

    @Autowired
    EmployeeFeignClient employeeFeignClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SalaryFeignClient salaryFeignClient;

    @Override
    public List<ListResponse> list(ListRequest employleesRequest) {
        String department = employleesRequest.getDepartment();
        Object raw = employeeFeignClient.getEmployeeByDepartment(department).getContent();
        List<Map<String, Object>> employees = objectMapper.convertValue(raw, new TypeReference<List<Map<String, Object>>>() {});
        List<ListResponse> employeeResponses = new ArrayList<>();
        for (Map<String, Object> employee : employees) {
            ListResponse response = new ListResponse();
            response.setJobId(String.valueOf(employee.get("jobId").toString()));
            response.setName(employee.get("name").toString());
            response.setDepartment(employee.get("department").toString());
            response.setSubDepartment(employee.get("subDepartment").toString());
            Object rawTitle = titleFeignClient.getTitleById(Long.valueOf(employee.get("title_id").toString()));
            Map<String, Object> titleMap = objectMapper.convertValue(rawTitle, new TypeReference<Map<String, Object>>() {});
            Title title = new Title();
            title.setId(Long.valueOf(titleMap.get("id").toString()));
            title.setName(titleMap.get("name").toString());
            title.setLevel(titleMap.get("level").toString());
            response.setTitle(title);
            response.setBaseSalary(null);
            raw = salaryFeignClient.getSalaryByJobId(response.getJobId()).getContent();
            List<Map<String, Object>> list = objectMapper.convertValue(raw, new TypeReference<List<Map<String, Object>>>() {});
            Map<String, Object> salaryMap = list.get(0);
            if (salaryMap != null && !salaryMap.isEmpty()) {
                response.setBaseSalary(Double.valueOf(salaryMap.get("baseSalary").toString()).longValue());
            } else {
                response.setBaseSalary(0L); // 默认值或处理逻辑
            }
            employeeResponses.add(response);

        }
        return employeeResponses;
    }

    @Override
    public void update(UpadteRequest request) {
        if(request.getDepartment() != null) {
            employeeFeignClient.updateDepartment(request.getJobId(), request.getDepartment());
        }
        if(request.getSubDepartment() != null) {
            employeeFeignClient.updateSubDepartment(request.getJobId(), request.getSubDepartment());
        }
        if (request.getBaseSalary() != null) {
            salaryFeignClient.updateBaseSalary(request.getJobId(), request.getBaseSalary());
        }
    }

    @Override
    public void batchSalary(BatchSalary request) {
        List<String> jobidList = employeeFeignClient.getJobIdListByDepartment(request.getDepartment());

        for (String jobid : jobidList) {
            salaryFeignClient.updateBaseSalary(jobid,request.getBaseSalary().doubleValue());
        }
    }


}
