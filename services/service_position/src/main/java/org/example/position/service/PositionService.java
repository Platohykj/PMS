package org.example.position.service;


import org.example.employee.model.Employee;
import org.example.position.model.Position;

import java.util.List;

public interface PositionService {

    void addPosition(Position position);

    List<Position> getPositionById(Long id);

    List<Position> getAllPositions();

    void deletePosition(Long id);
}
