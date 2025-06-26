package org.example.salarySet.service.impl;



import org.example.salarySet.model.SalarySet;
import org.example.salarySet.repository.SalarysetRepository;
import org.example.salarySet.service.SalarysetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SalarysetServiceImpl implements SalarysetService {

    @Autowired
    private SalarysetRepository salarysetRepository;

    @Override
    public List<SalarySet> getAllSalarySet() {
        List<SalarySet> salarySets = salarysetRepository.findAll();
        if (salarySets.isEmpty()) {
            throw new RuntimeException("No salary sets found");
        }
        return salarySets;
    }

    @Override
    public void addSalarySet(SalarySet salarySet) {
        if (salarySet == null) {
            throw new IllegalArgumentException("Salary set name cannot be null or empty");
        }
        salarysetRepository.save(salarySet);
    }

    @Override
    public void updateSalarySet(SalarySet salarySet) {
        if (salarySet == null || salarySet.getId() == null) {
            throw new IllegalArgumentException("Salary set or ID cannot be null");
        }
        if (!salarysetRepository.existsById(salarySet.getId())) {
            throw new RuntimeException("Salary set with ID " + salarySet.getId() + " does not exist");
        }
        salarysetRepository.save(salarySet);
    }

    @Override
    public void deleteSalarySet(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Salary set ID cannot be null");
        }
        if (!salarysetRepository.existsById(id)) {
            throw new RuntimeException("Salary set with ID " + id + " does not exist");
        }
        salarysetRepository.deleteById(id);
    }

    @Override
    public SalarySet getSalarySetById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Salary set ID cannot be null");
        }
        return salarysetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary set with ID " + id + " does not exist"));
    }
}
