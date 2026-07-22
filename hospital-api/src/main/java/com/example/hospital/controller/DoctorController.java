package com.example.hospital.controller;

import com.example.hospital.dto.ApiResponse;
import com.example.hospital.dto.DoctorDTO;
import com.example.hospital.exception.ResourceNotFoundException;
import com.example.hospital.model.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final List<Doctor> doctors = new ArrayList<>();

    // GET /api/v1/doctors
    @GetMapping
    public ApiResponse<List<Doctor>> getAllDoctors() {
        return new ApiResponse<>(HttpStatus.OK.value(), "success", doctors);
    }

    // GET /api/v1/doctors/{id}
    @GetMapping("/{id}")
    public ApiResponse<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id));
        return new ApiResponse<>(HttpStatus.OK.value(), "success", doctor);
    }

    // POST /api/v1/doctors
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Doctor> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        Doctor newDoctor = new Doctor();
        newDoctor.setId((long) (doctors.size() + 1));
        newDoctor.setFirstName(doctorDTO.getFirstName());
        newDoctor.setLastName(doctorDTO.getLastName());
        newDoctor.setSpecializationName(doctorDTO.getSpecializationName());
        newDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        newDoctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        newDoctor.setEmailAddress(doctorDTO.getEmailAddress());
        
        doctors.add(newDoctor);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "success", newDoctor);
    }

    // PUT /api/v1/doctors/{id}
    @PutMapping("/{id}")
    public ApiResponse<Doctor> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        Doctor existingDoctor = doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id));

        existingDoctor.setFirstName(doctorDTO.getFirstName());
        existingDoctor.setLastName(doctorDTO.getLastName());
        existingDoctor.setSpecializationName(doctorDTO.getSpecializationName());
        existingDoctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        existingDoctor.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        existingDoctor.setEmailAddress(doctorDTO.getEmailAddress());

        return new ApiResponse<>(HttpStatus.OK.value(), "success", existingDoctor);
    }

    // DELETE /api/v1/doctors/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDoctor(@PathVariable Long id) {
        boolean removed = doctors.removeIf(d -> d.getId().equals(id));
        if (!removed) {
            throw new ResourceNotFoundException("Không tìm thấy bác sĩ với ID: " + id);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "success");
    }
}
