package com.fmahadybd.school_app_service.model.domain;

import com.fmahadybd.school_app_service.enums.GuardianRelation;
import com.fmahadybd.school_app_service.enums.Religion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guardian{

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private GuardianRelation relation;
    private Religion religion;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updated;
}
