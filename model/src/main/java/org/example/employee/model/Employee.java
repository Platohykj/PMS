package org.example.employee.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.example.position.model.Position;
import org.example.salarySet.model.SalarySet;
import org.example.title.model.Title;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private String jobId;

    private String name;
    private String email;
    private String phone;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String idCard;
    private String maritalStatus;
    private String ethnicity;
    private String nativePlace;
    private String politicalStatus;
    private String address;
    private String department;
    private String subDepartment;
    private String employmentType;
    private LocalDate entryDate;
    private LocalDate promotionDate;
    private LocalDate contractStart;
    private LocalDate contractEnd;
    private Integer contractYears;
    private String education;
    private Long positionId;
    private Long title_id;
    private Long salary_set_id;
}
