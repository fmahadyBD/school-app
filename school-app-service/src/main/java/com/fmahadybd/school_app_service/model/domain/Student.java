package com.fmahadybd.school_app_service.model.domain;

import com.fmahadybd.school_app_service.enums.Religion;
import com.fmahadybd.school_app_service.enums.Sex;
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
public  class Student {
    private  Long id;
    private  String name;
    private  String email;
    private  String phoneNumber;
    private  String address;
    private  String dateOfBirth;
    private  String className;
    private  String profilePicture;
    private  String bcid;
    private  Sex sex;
    private  Religion religion;
    private  String bloodGroup;
    private  String groupSubject;
    private  String section;
    private  String status;
    private  List<SubjectSimple> subjects;
    private  List<GuardianSimple> guardians;
    private  LocalDateTime createdAt;
    private  LocalDateTime updateAt;
}
