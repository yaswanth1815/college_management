package com.practice.springBoot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.springBoot.entities.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,String>{
    boolean existsByBranch_Branchid(String id);
}