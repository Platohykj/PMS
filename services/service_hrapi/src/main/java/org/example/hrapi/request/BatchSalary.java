package org.example.hrapi.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BatchSalary {
    String department;
    BigDecimal baseSalary;
}
