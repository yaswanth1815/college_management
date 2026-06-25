package com.practice.springBoot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="branches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    private String branchid;
    private String branchname;
}
