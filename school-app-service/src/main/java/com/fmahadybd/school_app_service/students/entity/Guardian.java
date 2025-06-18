package com.fmahadybd.school_app_service.students.entity;

import com.fmahadybd.school_app_service.common.enums.Religion;
import com.fmahadybd.school_app_service.students.enums.GuardianRelation;

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
public class Guardian {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private GuardianRelation relation; // e.g., "father", "mother", "guardian"
    private Religion religion;

    private String createdAt; // Timestamp for when the guardian was created
    private String updatedAt; // Timestamp for when the guardian was last updated
    private String deletedAt; // Nullable, used for soft deletion
    private String status; // e.g., "active", "inactive"
}
