package com.fmahadybd.school_app_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TeacherRequestDTO(
        @NotBlank String name,
        @Email String email,
        @Pattern(regexp = "^[+]?[0-9]{10,15}$") String phoneNumber,
        String specialization,
        String status
) {
}
