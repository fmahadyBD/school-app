package com.fmahadybd.school_app_service.model.domain;

import com.fmahadybd.school_app_service.enums.SubjectGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject{

    private Long id;
    private String name;
    private String code;
    private String description;
    private String className;
    private SubjectGroup subjectGroup;
    private TeacherSimple teacher;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
