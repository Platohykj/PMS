package org.example.position.service.impl;


import org.example.position.model.Position;
import org.example.position.repository.PositionRepository;
import org.example.position.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;


    @Override
    public void addPosition(Position position) {
        // Logic to add a position
        positionRepository.save(position);
    }

    @Override
    public List<Position> getPositionById(Long id) {
        // Logic to retrieve a position by ID
        return positionRepository.findById(id).map(List::of).orElse(List.of());
    }

    @Override
    public List<Position> getAllPositions() {
        // Logic to retrieve all positions
        List<Position> positions = positionRepository.findAll();
        if (positions.isEmpty()) {
            return List.of(); // Return an empty list if no positions found
        }
        return positions;
    }

    @Override
    public void deletePosition(Long id) {
        // Logic to delete a position by ID
        Optional<Position> position = positionRepository.findById(id);
        if (position.isPresent()) {
            positionRepository.delete(position.get());
        } else {
            throw new RuntimeException("Position not found with id: " + id);
        }
    }
}
