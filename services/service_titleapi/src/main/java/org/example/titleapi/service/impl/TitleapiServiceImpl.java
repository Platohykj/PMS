package org.example.titleapi.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.response.Response;
import org.example.title.model.Title;
import org.example.titleapi.feign.EmployeeFeignClient;
import org.example.titleapi.feign.TitleFeignClient;
import org.example.titleapi.request.EmployleesRequest;
import org.example.titleapi.request.RemoveRequest;
import org.example.titleapi.request.UpdateEmployeeRequest;
import org.example.titleapi.response.EmployeeResponse;
import org.example.titleapi.service.TitleapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TitleapiServiceImpl implements TitleapiService {


    @Autowired
    TitleFeignClient titleFeignClient;

    @Autowired
    EmployeeFeignClient employeeFeignClient;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Response<?> getTitleList() {
        return titleFeignClient.getAllTitle();
    }

    @Override
    public List<String> getTitleNames() {
        Object raw = titleFeignClient.getAllTitle().getContent();

        List<Map<String, Object>> rawList = (List<Map<String, Object>>) raw;

        List<String> names = rawList.stream()
                .map(item -> item.get("name").toString())
                .collect(Collectors.toList());

        System.out.println(names);  // 输出: [职称名称, ...]
        return names;
    }

    @Override
    public Response<?> updateTitle(Title title) {
        return titleFeignClient.updateTitle(title);
    }

    @Override
    public Response<?> saveTitle(Title title) {
        return titleFeignClient.addTitle(title);
    }

    @Override
    public void deleteTitle(Long id) {
        titleFeignClient.deleteTitle(id);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(EmployleesRequest employleesRequest) {
        String department = employleesRequest.getDepartment();
        Object raw = employeeFeignClient.getEmployeeByDepartment(department).getContent();
        List<Map<String, Object>> employees = objectMapper.convertValue(raw, new TypeReference<List<Map<String, Object>>>() {});
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Map<String, Object> employee : employees) {
            EmployeeResponse response = new EmployeeResponse();
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
            employeeResponses.add(response);
        }
        return employeeResponses;

    }

    @Override
    public void updateEmployeePosition(UpdateEmployeeRequest updateEmployeeRequest) {
        employeeFeignClient.updateTitleId(updateEmployeeRequest.getJobId(), updateEmployeeRequest.getTitleId());
    }

    @Override
    public void removeEmployeeTitle(RemoveRequest removeRequest) {
        employeeFeignClient.removeTitle(removeRequest.getJobId());
    }
}
