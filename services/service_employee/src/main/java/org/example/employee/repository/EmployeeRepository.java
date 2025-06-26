package org.example.employee.repository;


import org.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByJobId(String jobId);

    List<Employee> findByDepartment(String department);
}
