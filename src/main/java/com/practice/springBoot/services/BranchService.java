package com.practice.springBoot.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.springBoot.entities.Branch;
import com.practice.springBoot.repositories.BranchRepository;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch addBranchToTable(Branch br){
        Optional<Branch> brch=branchRepository.findById(br.getBranchid());
        if(brch.isPresent()){
            return null;
        }
        return branchRepository.save(br);
    }

    public Branch findingBranchById(String brid){
        Optional<Branch> brc=branchRepository.findById(brid);
        if(brc.isPresent()){
            return brc.get();
        }
        else{
            return null;
        }
    }

    public List<Branch> showAllBranches() {
        return branchRepository.findAll();
    }
}
