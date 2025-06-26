package org.example.salarySet.repository;



import org.example.salarySet.model.SalarySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalarysetRepository extends JpaRepository<SalarySet, Long> {

}
