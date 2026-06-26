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
import com.practice.springBoot.repositories.FacultyRepository;
import com.practice.springBoot.services.BranchService;
import com.practice.springBoot.services.FacultyService;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private FacultyRepository facultyRepository;

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

    @PostMapping("/updatefacultytable")
    public String updateFaculty(Faculty faculty,RedirectAttributes reda){
        Faculty existfac=facultyService.findFacultyById(faculty.getFacultyid());
        if(existfac!=null){
            if(faculty.getBranch()!=null && faculty.getBranch().getBranchid()!=null){
                Branch brch=branchService.findingBranchById(faculty.getBranch().getBranchid());
                if(brch==null){
                    reda.addFlashAttribute("updatemessage","Selected Branch Not Available");
                    return "redirect:/openupdatefaculties";
                }
                else{
                    existfac.setBranch(brch);
                }
            }
            if(!faculty.getDesignation().equals("")){
                existfac.setDesignation(faculty.getDesignation());
            }
            System.out.println("faculty name:"+faculty.getFacultyname());
            if(!faculty.getFacultyname().equals("")){
                existfac.setFacultyname(faculty.getFacultyname());
            }
            facultyRepository.save(existfac);
            reda.addFlashAttribute("updatemessage","Update Successful");
        }
        else{
            reda.addFlashAttribute("updatemessage","Details Not Found");
        }
        return "redirect:/openupdatefaculties";
    }
}
