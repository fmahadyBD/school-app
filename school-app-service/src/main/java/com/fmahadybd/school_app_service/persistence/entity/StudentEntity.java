package com.fmahadybd.school_app_service.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fmahadybd.school_app_service.enums.Sex;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fmahadybd.school_app_service.enums.Religion;

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
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String className; // 9,10,8

    private String profilePicture;
    private String bcid;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    private String bloodGroup; // e.g., "A+", "B-", etc.

    @ManyToMany
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SubjectEntity> subjects; // List of Subject entities associated with the student

    private String groupSubject;

    // TODO: Add a field for the section of the class, e.g.,
    // "Boyes","Girls","Regular"
    private String section;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<GuardianEntity> guardians; // List of Guardian entities associated with the student

    private String status; // e.g., "active", "inactive", "graduated"

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    // helper methods for bidirectional relations

    public void addGuardian(GuardianEntity guardian) {
        guardians.add(guardian);
        guardian.setStudent(this);
    }
    public void removeGuardian(GuardianEntity guardian) {
        guardians.remove(guardian);
        guardian.setStudent(null);
    }
}
