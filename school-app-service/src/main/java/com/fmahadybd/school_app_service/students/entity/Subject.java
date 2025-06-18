package com.fmahadybd.school_app_service.students.entity;

import com.fmahadybd.school_app_service.students.enums.SubjectGroup;

import jakarta.persistence.Column;
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
public class Subject {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String code;
    private String description;
    private String className; // e.g., "Class 10", "Class 11"
    private SubjectGroup group; // e.g., "Science", "Arts", "Commerce","Regular"

    private String createdAt; // Timestamp for when the subject was created
    private String updatedAt; // Timestamp for when the subject was last updated
    private String deletedAt; // Nullable, used for soft deletion
    private String status; // e.g., "active", "inactive"

    // TODO: add a Manye to One relation with teacher entity
    private String teacherId; // ID of the teacher assigned to this subject
}
// Note: The `teacherId` field is a placeholder for the ID of the teacher
// assigned to this subject.
