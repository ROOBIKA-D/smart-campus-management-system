package com.smartcampus.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="faculty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable=false)
    private String facultyId;

    private String name;

    @Column(unique=true)
    private String email;

    private String department;

    private String designation;

    private String phone;
}