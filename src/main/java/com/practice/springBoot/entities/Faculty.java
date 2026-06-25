package com.practice.springBoot.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="faculty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    private String facultyid;
    private String facultyname;
    private String designation;

    @ManyToOne
    @JoinColumn(name="branchuid", referencedColumnName = "branchid")
    private Branch branch;
}
