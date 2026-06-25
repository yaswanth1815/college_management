package com.practice.springBoot.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.practice.springBoot.entities.Branch;
import com.practice.springBoot.entities.Faculty;
import com.practice.springBoot.services.BranchService;
import com.practice.springBoot.services.FacultyService;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private BranchService branchService;

    @PostMapping("/addfacultytotable")
    public String addFacultyToTable(Faculty fac,Model model){
        
        Branch brch=branchService.findingBranchById(fac.getBranch().getBranchid());
        if(brch!=null){
            fac.setBranch(brch);
            Faculty f=facultyService.addFacultyToTable(fac);
            if(f==null){
                model.addAttribute("facultymessage","User Already Found with this ID");
            }
            else{
                model.addAttribute("facultymessage","Faculty Added Successfully");
            }
        }
        else{
            model.addAttribute("facultymessage","Branch Doesn't Exist");
        }
        return "addfaculty";
    }


    @GetMapping("/openshowfaculty")
    public String showAllFaculty(Model model){
        List<Faculty> listoffaculty=facultyService.showAllFaculty();
        model.addAttribute("listoffaculty",listoffaculty);
        return "showfaculty";
    }

    @PostMapping("/deletefacultyfromtable")
    public String deleteFaculty(String facultyid,RedirectAttributes redas){
        String ss=facultyService.deleteFaculty(facultyid);
        redas.addFlashAttribute("deletedids",ss);
        return "redirect:/opendelete";
    }
}
