package org.example.positionapi.request;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    String jobId;
    String position;
}
