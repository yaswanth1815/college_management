package com.practice.springBoot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.springBoot.entities.Students;

public interface StudentsRepository extends JpaRepository<Students,String>{
    boolean existsByBranch_Branchid(String id);
}