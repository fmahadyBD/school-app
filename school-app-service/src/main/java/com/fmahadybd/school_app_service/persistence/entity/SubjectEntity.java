package com.fmahadybd.school_app_service.persistence.entity;

import com.fmahadybd.school_app_service.enums.SubjectGroup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;
    private String description;
    private String className; // e.g., "Class 10", "Class 11"

    @Column(name = "subject_group")
    private SubjectGroup subjectGroup; // e.g., "Science", "Arts", "Commerce","Regular"

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private String createdAt; // Timestamp for when the subject was created

    @UpdateTimestamp
    private String updatedAt; // Timestamp for when the subject was last updated

    private String deletedAt; // Nullable, used for soft deletion

    // TODO Manke enumatration
    private String status; // e.g., "active", "inactive"


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
}
// Note: The `teacherId` field is a placeholder for the ID of the teacher
// assigned to this subject.
