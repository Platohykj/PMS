package org.example.salarySet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "salary_sets")
public class SalarySet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;
    private String subDepartment;
    private BigDecimal baseSalary;
    private BigDecimal transportAllowance;
    private BigDecimal lunchAllowance;
    private BigDecimal bonus;
    private BigDecimal pensionRate;
    private BigDecimal pensionBase;
    private BigDecimal medicalRate;
    private BigDecimal medicalBase;
    private BigDecimal fundRate;
    private BigDecimal fundBase;
}
