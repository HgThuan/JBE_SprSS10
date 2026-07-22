package com.example.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String specializationName;
    private String phoneNumber;
    private Integer yearsOfExperience;
    private String emailAddress;
}
