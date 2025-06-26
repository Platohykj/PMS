package org.example.modification.service.impl;



import org.example.modification.model.ModificationApplication;
import org.example.modification.repository.ModificationRepository;
import org.example.modification.service.ModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModificationServiceImpl implements ModificationService {
    @Autowired
    private ModificationRepository modificationRepository;

    @Override
    public List<ModificationApplication> getAllModifications() {
        List<ModificationApplication> modifications = modificationRepository.findAll();
        if (modifications.isEmpty()) {
            throw new RuntimeException("No modifications found");
        }
        return modifications;
    }

    @Override
    public ModificationApplication addModification(ModificationApplication modificationApplication) {
        if (modificationApplication == null) {
            throw new IllegalArgumentException("Modification application cannot be null");
        }
        return modificationRepository.save(modificationApplication);
    }

    @Override
    public ModificationApplication updateStatus(Long id, String status) {
        ModificationApplication modification = modificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modification with id " + id + " not found"));

        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        modification.setStatus(ModificationApplication.Status.valueOf(status));
        return modificationRepository.save(modification);
    }
}
