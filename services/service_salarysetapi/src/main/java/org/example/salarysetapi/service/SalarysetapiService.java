package org.example.salarysetapi.service;


import org.example.position.model.Position;
import org.example.response.Response;
import org.example.salarySet.model.SalarySet;

import java.io.IOException;
import java.util.List;

public interface SalarysetapiService {

    Response<?> list();

    Response<?> saveSalarySet(SalarySet salarySet);

    Response<?> updateSalarySet(SalarySet salarySet);

    Response<?> deleteSalarySet(Long id);
}
