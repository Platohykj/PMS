package org.example.positionapi.respose;

import lombok.Data;

@Data
public class EmployeesRespose {
    String jobId;
    String name;
    String department;
    String subDepartment;
    Long positionId;
    String position;
}
