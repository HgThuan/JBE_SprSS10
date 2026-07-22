package com.example.hospital.controller;

import com.example.hospital.dto.ApiResponse;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Prescription;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final List<Prescription> prescriptions = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    // Ex03: GET /api/v1/doctors/{doctorId}/appointments
    @GetMapping("/doctors/{doctorId}/appointments")
    public ApiResponse<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> doctorAppointments = appointments.stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", doctorAppointments);
    }

    // Ex03: GET /api/v1/patients/{patientId}/prescriptions/{prescriptionId}
    @GetMapping("/patients/{patientId}/prescriptions/{prescriptionId}")
    public ApiResponse<Prescription> getPrescriptionByPatientAndId(
            @PathVariable Long patientId, @PathVariable Long prescriptionId) {
            
        Prescription prescription = prescriptions.stream()
                .filter(p -> p.getPatientId().equals(patientId) && p.getId().equals(prescriptionId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy đơn thuốc ID: " + prescriptionId + " cho bệnh nhân ID: " + patientId));
                        
        return new ApiResponse<>(HttpStatus.OK.value(), "success", prescription);
    }

    // Ex03: POST /api/v1/patients/{patientId}/prescriptions
    @PostMapping("/patients/{patientId}/prescriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Prescription> createPrescription(
            @PathVariable Long patientId, @RequestBody Prescription prescription) {
            
        prescription.setId((long) (prescriptions.size() + 1));
        prescription.setPatientId(patientId);
        prescriptions.add(prescription);
        
        return new ApiResponse<>(HttpStatus.CREATED.value(), "success", prescription);
    }
}
