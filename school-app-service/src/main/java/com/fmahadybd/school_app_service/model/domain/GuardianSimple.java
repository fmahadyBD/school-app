package com.fmahadybd.school_app_service.model.domain;

import com.fmahadybd.school_app_service.enums.GuardianRelation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuardianSimple{

    private Long id;
    private String name;
    private String phoneNumber;
    private GuardianRelation relation;
}
