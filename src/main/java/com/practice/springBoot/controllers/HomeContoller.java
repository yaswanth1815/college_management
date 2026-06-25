package com.practice.springBoot.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller {
    @RequestMapping("/")
    public String openHomePage(){
        return "home";
    }
    
    @RequestMapping("/openaddstudent")
    public String openAddStudent(){
        return "addstudent";
    }

    @RequestMapping("openshowstudents")
    public String openShowStudents(){
        return "showstudents";
    }

    @RequestMapping("openupdatestudents")
    public String openUpdateStudents(){
        return "updatestudent";
    }

    @RequestMapping("openaddfaculty")
    public String openAddFaculty(){
        return "addfaculty";
    }

    @RequestMapping("openshowfaculty")
    public String openShowFaculty(){
        return "showfaculty";
    }

    @RequestMapping("openupdatefaculties")
    public String openupdatefaculty(){
        return "updatefaculty";
    }

    @RequestMapping("openaddbranch")
    public String openaddbranch(){
        return "addbranch";
    }

    @RequestMapping("openshowbranch")
    public String openshowbranches(){
        return "showbranch";
    }

    @RequestMapping("openupdatebranches")
    public String openupdatebranches(){
        return "updatebranch";
    }

    @RequestMapping("opendelete")
    public String opendelete(){
        return "delete";
    }

}
