package com.fmahadybd.school_app_service.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher{

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;
    private List<SubjectSimple> subjects;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
