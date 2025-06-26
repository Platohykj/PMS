package org.example.salary.repository;


import org.example.position.model.Position;
import org.example.salary.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findByJobId(String jobId);
}
