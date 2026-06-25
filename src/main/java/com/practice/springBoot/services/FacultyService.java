package com.practice.springBoot.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.springBoot.entities.Faculty;
import com.practice.springBoot.repositories.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty addFacultyToTable(Faculty f){
        Optional<Faculty> fac=facultyRepository.findById(f.getFacultyid());
        if(fac.isPresent()){
            return null;
        }
        return facultyRepository.save(f);
    }

    public List<Faculty> showAllFaculty(){
        return facultyRepository.findAll();
    }

    public String deleteFaculty(String facultyids) {
        String[] ids=facultyids.split(",");
        int count1=0;
        int count2=0;
        String dele="";
        String notdele="";
        String mainStr="";
        for(String id:ids){
            if(facultyRepository.existsById(id)){
                count1++;
                dele=dele+" "+id;
                facultyRepository.deleteById(id);
            }
            else{
                notdele=notdele+" "+id;
            }
        }
        if(count1>0){
            mainStr=mainStr+" Deleted ID's:" +dele;
        }
        else{
            mainStr=mainStr+" Not Deleted ID's"+notdele;
        }
        return mainStr;
    }
}
