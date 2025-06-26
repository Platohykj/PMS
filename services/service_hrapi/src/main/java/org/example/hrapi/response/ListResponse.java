package org.example.hrapi.response;

import lombok.Data;
import org.example.title.model.Title;

@Data
public class ListResponse {
    String jobId;
    String name;
    String department;
    String subDepartment;
    Title title;
    Long baseSalary;
}
