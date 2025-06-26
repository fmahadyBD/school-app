package com.fmahadybd.school_app_service.model.domain;

import com.fmahadybd.school_app_service.enums.SubjectGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectSimple{

    private Long id;
    private String name;
    private String code;
    private SubjectGroup subjectGroup;
}
