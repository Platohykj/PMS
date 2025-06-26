package org.example.hrapi.request;

import lombok.Data;

@Data
public class UpadteRequest {
    String jobId;
    String department;
    String subDepartment;
    Long baseSalary;
}
