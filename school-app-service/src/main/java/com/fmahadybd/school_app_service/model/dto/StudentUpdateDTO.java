package com.fmahadybd.school_app_service.model.dto;

import com.fmahadybd.school_app_service.enums.Religion;
import com.fmahadybd.school_app_service.enums.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record StudentUpdateDTO(
        @NotBlank @Size(max = 100) String name,
        Integer classRoll,
        @Email String email,
        @Pattern(regexp = "^[+]?[0-9]{10,15}$") String phoneNumber,
        String address,
        LocalDate dateOfBirth,
        String className,
        String profilePicture,
        String bcid,
        Sex sex,
        Religion religion,
        String bloodGroup,
        String groupSubject,
        String section,
        String status,
        List<Long> subjectIds, // ManyToMany
        List<GuardianRequest> guardians // For OneToMany with Guuardian
) {
}
