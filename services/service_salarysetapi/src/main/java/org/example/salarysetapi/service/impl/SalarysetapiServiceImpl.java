package org.example.salarysetapi.service.impl;


import org.example.response.Response;
import org.example.salarySet.model.SalarySet;
import org.example.salarysetapi.feign.SalarysetFeignClient;
import org.example.salarysetapi.service.SalarysetapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalarysetapiServiceImpl implements SalarysetapiService {

    @Autowired
    private SalarysetFeignClient salarysetFeignClient;



    @Override
    public Response<?> list() {
        return salarysetFeignClient.getAllSalarySet();
    }

    @Override
    public Response<?> saveSalarySet(SalarySet salarySet) {
        return salarysetFeignClient.addSalarySet(salarySet);
    }

    @Override
    public Response<?> updateSalarySet(SalarySet salarySet) {
        return salarysetFeignClient.updateSalarySet(salarySet);
    }

    @Override
    public Response<?> deleteSalarySet(Long id) {
        return salarysetFeignClient.deleteSalarySet(id);
    }
}
