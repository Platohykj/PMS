package org.example.salary.service;


import org.example.position.model.Position;
import org.example.salary.model.Salary;

import java.util.List;

public interface SalaryService {

    void addSalary(Salary salary);

    List<Salary> getSalaryByJobId(String jobId);

    List<Salary> getAllSalaries();
}
