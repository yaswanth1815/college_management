package com.practice.springBoot.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.practice.springBoot.entities.Branch;
import com.practice.springBoot.entities.Faculty;
import com.practice.springBoot.services.BranchService;

@Controller
public class BranchController {

    @Autowired
    private BranchService branchService;
    @PostMapping("/addbranchtotable")
    public String addBranchToTable(Branch b,Model model){
        Branch bh=branchService.addBranchToTable(b);
        if(bh!=null){
            model.addAttribute("branchmessage", "Branch Added Successfully");
        }
        else{
            model.addAttribute("branchmessage","Something Went Wrong");
        }
        return "addbranch";
    }

    @GetMapping("/openshowbranch")
    public String showAllBranches(Model model){
        List<Branch> listofbranches=branchService.showAllBranches();
        model.addAttribute("listofbranches",listofbranches);
        return "showbranch";
    }
}
