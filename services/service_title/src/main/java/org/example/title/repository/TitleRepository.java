package org.example.title.repository;



import org.example.salarySet.model.SalarySet;
import org.example.title.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}
