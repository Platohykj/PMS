package org.example.modification.repository;


import org.example.modification.model.ModificationApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@EntityScan(basePackages = "org/example/modification/model")


@Repository
public interface ModificationRepository extends JpaRepository<ModificationApplication, Long> {

}
