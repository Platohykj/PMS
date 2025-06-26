package org.example.department.repository;


import org.example.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByName(String name);

    @Query("SELECT d FROM Department d WHERE d.parent_id = :parentId") //
    List<Department> findByParentId(Long parentId);
}
