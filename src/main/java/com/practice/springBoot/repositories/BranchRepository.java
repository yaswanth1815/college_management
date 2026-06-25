package com.practice.springBoot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.springBoot.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch,String>{
    
}
