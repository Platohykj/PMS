package org.example.salaryapi.service.impl;


import org.example.response.Response;
import org.example.salaryapi.feign.SalaryFeignClient;
import org.example.salaryapi.service.SalaryapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryapiService {

    @Autowired
    private SalaryFeignClient salaryFeignClient;

    @Override
    public Response<?> queryAll() {
        return salaryFeignClient.getAllSalaries();
    }
}
