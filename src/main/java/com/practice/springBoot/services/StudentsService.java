package com.practice.springBoot.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.springBoot.entities.Students;
import com.practice.springBoot.repositories.StudentsRepository;

@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentsRepository;

    public Students addStudentToTable(Students s){
        Optional<Students> stud=studentsRepository.findById(s.getStudentroll());
        if(stud.isPresent()){
            return null;
        }
        return studentsRepository.save(s);
    }

    public List<Students> showAllStudents(){
        return studentsRepository.findAll();
    }

    public String deleteStudents(String studentids){
        String[] ids=studentids.split(",");
        String retu1="";
        int count1=0;
        int count2=0;
        String retu2="";
        for(String id:ids){
            if(studentsRepository.existsById(id)){
                count1++;
                studentsRepository.deleteById(id);
                retu1=retu1+"  "+id;
            }
            else{
                count2++;
                retu2=retu2+" "+id;
            }
        }
        String returnthis="";
        if (count1>0){
            returnthis=returnthis+"Delete Success: "+retu1;
        }
        if(count2>0){
            returnthis=returnthis+"Delete Failed: "+retu2;
        }
        return  returnthis;
    }
}
