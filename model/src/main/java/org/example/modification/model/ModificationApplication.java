package org.example.modification.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.employee.model.Employee;

@Data
@Entity
@Table(name = "modification_applications")
public class ModificationApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field;
    private String oldValue;
    private String newValue;
    private String proofImage;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String approver;
    private String remark;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    private String job_id;
}

