package com.fmahadybd.school_app_service.model.dto;

import com.fmahadybd.school_app_service.enums.SubjectGroup;
import jakarta.validation.constraints.NotBlank;

public record SubjectRequestDTO (
        @NotBlank String name,
        String code,
        String description,
        String className,
        SubjectGroup subjectGroup,
        String status,
        Long teacherId // Reference to teacher // it can be blank

){
}
