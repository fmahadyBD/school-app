package com.fmahadybd.school_app_service.students.entity;

import java.util.List;

import com.fmahadybd.school_app_service.common.enums.Religion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer classRoll;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    private String className;

    private String profilePicture;
    private String bcid;
    private String sex;
    private Religion religion;
    private String bloodGroup; // e.g., "A+", "B-", etc.

    private List<Subject> subjects; // List of Subject entities associated with the student
    private String groupSubject;

    // TODO: Add a field for the section of the class, e.g., "Boyes","Girls","Regular"
    private String section;

    private List<Guardian> guardians; // List of Guardian entities associated with the student

    private String status; // e.g., "active", "inactive", "graduated"
    private String createdAt;
    private String updatedAt;
    private String deletedAt; // Nullable, used for soft deletion

}
