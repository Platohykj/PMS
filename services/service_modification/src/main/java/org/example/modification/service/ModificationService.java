package org.example.modification.service;


import org.example.employee.model.Employee;
import org.example.modification.model.ModificationApplication;

import java.util.List;

public interface ModificationService {


    List<ModificationApplication> getAllModifications();

    ModificationApplication addModification(ModificationApplication modificationApplication);

    ModificationApplication updateStatus(Long id, String status);
}
