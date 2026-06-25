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
@Table(name="students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    @Id
    private String studentroll;
    private String studentname;
    private int curryear;
    private int currsemester;

    @ManyToOne
    @JoinColumn(name="branchkey", referencedColumnName = "branchid")
    private Branch branch;
}
