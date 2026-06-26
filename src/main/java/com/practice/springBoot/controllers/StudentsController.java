package com.practice.springBoot.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.practice.springBoot.entities.Branch;
import com.practice.springBoot.entities.Students;
import com.practice.springBoot.repositories.StudentsRepository;
import com.practice.springBoot.services.BranchService;
import com.practice.springBoot.services.StudentsService;

@Controller
public class StudentsController {

    @Autowired
    private StudentsService studentsService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private StudentsRepository studentRepository;

    @PostMapping("/addingstudenttotable")
    public String addingStudentToTable(Students student,Model model){
        
        Branch brch=branchService.findingBranchById(student.getBranch().getBranchid());
        if(brch==null){
            model.addAttribute("addingmessage","Branch Not Found");
        }
        else{
            student.setBranch(brch);
            Students stud=studentsService.addStudentToTable(student);
            if(stud==null){
                model.addAttribute("addingmessage","Student with this RollNumber already Found");
            }
            else{
                model.addAttribute("addingmessage","Student Added Successfully");
            }
        }
        return "addstudent";
    }

    @GetMapping("/openshowstudents")
    public String showAllStudents(Model model){
        List<Students> stdlst=studentsService.showAllStudents();
        System.out.println(stdlst.size()); 
        model.addAttribute("listofstudents", stdlst);
        return "showstudents";
    }

    @PostMapping("/deletestudentfromtable")
    public String deleteStudents(String studentids, RedirectAttributes red){
        String ss=studentsService.deleteStudents(studentids);
        red.addFlashAttribute("deletedids",ss);
        return "redirect:/opendelete";
    }

    @PostMapping("/updatestudentintable")
    public String updateStudent(Students student,RedirectAttributes reda){
        Students existStud=studentsService.findStudentById(student.getStudentroll());
        if(existStud!=null){
            if(student.getBranch().getBranchid()!=null){
                Branch brch=branchService.findingBranchById(student.getBranch().getBranchid());
                if(brch==null){
                    reda.addFlashAttribute("updatemessage","Selected Branch Not Available");
                }
                else{
                    existStud.setBranch(brch);
                }
            }
            if(!student.getStudentname().equals("")){
                existStud.setStudentname(student.getStudentname());
            }
            if(student.getCurrsemester()>0){
                existStud.setCurrsemester(student.getCurrsemester());
            }
            if(student.getCurryear()>0){
                existStud.setCurryear(student.getCurryear());
            }
            reda.addFlashAttribute("updatemessage","Update Successful");
            studentRepository.save(existStud);
        }
        else{
            reda.addFlashAttribute("updatemessage","Details Not Found");
        }
        return "redirect:/openupdatestudents";
    }
}
