package com.fmahadybd.school_app_service.persistence.entity;

import com.fmahadybd.school_app_service.enums.Religion;
import com.fmahadybd.school_app_service.enums.GuardianRelation;

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
@Table(name="guardians")
public class GuardianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    private GuardianRelation relation; // e.g., "father", "mother", "guardian"

    @Enumerated(EnumType.STRING)
    private Religion religion;

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;



    @CreationTimestamp
    @Column(updatable = false)
    private String createdAt; // Timestamp for when the guardian was created

    @UpdateTimestamp
    private String updatedAt; // Timestamp for when the guardian was last updated

    private String deletedAt; // Nullable, used for soft deletion
    private String status; // e.g., "active", "inactive"
}
