package org.example.titleapi.response;

import lombok.Data;
import org.example.title.model.Title;

@Data
public class EmployeeResponse {
    String jobId;
    String name;
    String department;
    String subDepartment;
    Title title;
}
