package com.fmahadybd.school_app_service.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSimple{

    private Long id;
    private String name;
    private String specialization;
    private String phoneNumber;
}
