package org.example.salary.service.impl;



import org.example.salary.model.Salary;
import org.example.salary.repository.SalaryRepository;
import org.example.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public void addSalary(Salary salary) {
        salaryRepository.save(salary);
    }

    @Override
    public List<Salary> getSalaryByJobId(String jobId) {
        return salaryRepository.findByJobId(jobId);
    }

    @Override
    public List<Salary> getAllSalaries() {
        List<Salary> salaries = salaryRepository.findAll();
        if (!salaries.isEmpty()) {
            return salaries;
        } else {
            return Collections.emptyList(); // 返回空列表而不是null
        }
    }

    @Override
    public void updateSalary(Salary salary) {
        salaryRepository.save(salary);
    }

}
