package org.example.positionapi.service;


import org.example.position.model.Position;
import org.example.positionapi.request.RemoveEmployeePositionRequest;
import org.example.positionapi.request.SaveRequest;
import org.example.positionapi.request.UpdateEmployeeRequest;
import org.example.positionapi.respose.EmployeesRespose;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PositionapiService {

    List<Position> getPositionList();

    void savePosition(Position position);

    void deletePosition(Long id);

    List<EmployeesRespose> getEmployeesByPosition(String department);

    void updateEmployeePosition(UpdateEmployeeRequest updateEmployeeRequest) throws IOException;

    void removeEmployeePosition(RemoveEmployeePositionRequest removeEmployeePositionRequest);
}
