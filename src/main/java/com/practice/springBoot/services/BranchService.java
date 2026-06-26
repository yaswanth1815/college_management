package com.practice.springBoot.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.springBoot.entities.Branch;
import com.practice.springBoot.repositories.BranchRepository;
import com.practice.springBoot.repositories.FacultyRepository;
import com.practice.springBoot.repositories.StudentsRepository;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentsRepository studentsRepository;

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

    public String deleteFromTable(String branchids) {
        String[] ids=branchids.split(",");
        int count1=0;
        int count2=0;
        String dele="";
        String notdele="";
        String mainout="";
        for(String id:ids){
            boolean check1=studentsRepository.existsByBranch_Branchid(id);
            boolean check2=facultyRepository.existsByBranch_Branchid(id);
            boolean check3=branchRepository.existsById(id);
            if(check1 || check2 || !check3){
                count2++;
                notdele=notdele+" "+id;
            }
            else{
                count1++;
                dele=dele+id;
                branchRepository.deleteById(id);
            }
        }
        if(count1>0){
            mainout=mainout+" Deleted ID's: "+dele;
        }
        if(count2>0){
            mainout=mainout+" Not Deleted ID's: "+notdele;
        }
        return mainout;
    }
    

    public Branch updateBranch(Branch brch){
        Branch exisbrch=findingBranchById(brch.getBranchid());
        if(exisbrch!=null){
            exisbrch.setBranchname(brch.getBranchname());
            return branchRepository.save(exisbrch);
        }
        return null;
    }
}
