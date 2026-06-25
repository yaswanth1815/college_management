package com.practice.springBoot.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.practice.springBoot.entities.Branch;
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

    @PostMapping("/deletebranchfromtable")
    public String delteBranches(String branchids, RedirectAttributes reda){
        String ss=branchService.deleteFromTable(branchids);
        reda.addFlashAttribute("deletedids",ss);
        return "redirect:/opendelete";
    }
    @PostMapping("/updatebranchdata")
    public String updateBranch(Branch brch,Model model){
        Branch br=branchService.updateBranch(brch);
        if(br==null){
            model.addAttribute("updatemessage","Something Went Wrong");
        }
        else{
            model.addAttribute("updatemessage","Updated Successfully");
        }
        return "updatebranch";

    }
}
