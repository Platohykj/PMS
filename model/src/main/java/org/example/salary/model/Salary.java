package org.example.salary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String salaryMonth;
    private BigDecimal baseSalary;
    private BigDecimal actualSalary;
    private BigDecimal transportAllowance;
    private BigDecimal lunchAllowance;
    private BigDecimal bonus;
    private BigDecimal pension;
    private BigDecimal medicalInsurance;
    private BigDecimal housingFund;
    private String jobId;
}