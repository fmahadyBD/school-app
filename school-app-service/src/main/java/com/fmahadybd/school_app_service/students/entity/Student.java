package com.fmahadybd.school_app_service.students.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fmahadybd.school_app_service.common.enums.Religion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String name;
    private Integer classRoll;

    @Email
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$")
    private String phoneNumber;
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String className;

    private String profilePicture;
    private String bcid;
    private String sex;
    private Religion religion;
    private String bloodGroup; // e.g., "A+", "B-", etc.

    @OneToMany
    private List<Subject> subjects; // List of Subject entities associated with the student
    private String groupSubject;

    // TODO: Add a field for the section of the class, e.g.,
    // "Boyes","Girls","Regular"
    private String section;

    @OneToMany
    private List<Guardian> guardians; // List of Guardian entities associated with the student

    private String status; // e.g., "active", "inactive", "graduated"
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
