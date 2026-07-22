package com.example.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private Long id;
    private Long patientId;
    private String medicationName;
    private String dosage;
    private String instructions;
}
