package org.example.salarySet.service;


import org.example.salarySet.model.SalarySet;

import java.util.List;

public interface SalarysetService {

    List<SalarySet> getAllSalarySet();

    void addSalarySet(SalarySet salarySet);

    void updateSalarySet(SalarySet salarySet);

    void deleteSalarySet(Long id);
}
